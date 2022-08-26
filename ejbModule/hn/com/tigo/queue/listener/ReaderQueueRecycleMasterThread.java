/**
 * ReaderQueueRecycleMasterThread.java
 * ReaderQueueRecycleMasterThread
 * Copyright (c) Tigo Honduras.
 */
package hn.com.tigo.queue.listener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.tigo.josm.gateway.services.order.additionalparameterdto.v1.AdditionalParameters;
import com.tigo.josm.gateway.services.order.additionalparameterdto.v1.Parameter;
import com.tigo.josm.gateway.services.order.complexorderrequest.v1.ComplexOrderRequest;
import com.tigo.josm.gateway.services.order.complexorderrequest.v1.EnumOrderType;
import com.tigo.josm.gateway.services.order.complexorderrequest.v1.OrderRequestDetail;
import com.tigo.josm.gateway.services.order.orderresponse.v1.OrderResponse;

import hn.com.tigo.josm.persistence.core.ServiceSessionEJB;
import hn.com.tigo.josm.persistence.core.ServiceSessionEJBLocal;
import hn.com.tigo.josm.persistence.exception.PersistenceException;
import hn.com.tigo.queue.dto.ConfigEventDTO;
import hn.com.tigo.queue.dto.DetailEventDTO;
import hn.com.tigo.queue.dto.NotifyMessageDTO;
import hn.com.tigo.queue.listener.entity.OcepManager;
import hn.com.tigo.queue.service.CPEComplexServiceProvider;
import hn.com.tigo.queue.utils.QueueConstantListener;
import hn.com.tigo.queue.utils.ReadFilesConfig;
import hn.com.tigo.queue.utils.States;

/**
 * ReaderQueueRecycleMasterThread.
 *
 * @author Yuny Rene Rodriguez Perez {@literal<mailto: yrodriguez@hightech-corp.com />}
 * @version  1.0.0
 * @since 08-25-2022 10:17:53 PM 2022
 */
public class ReaderQueueRecycleMasterThread extends Thread {

	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LogManager.getLogger(ReaderQueueRecycleMasterThread.class);
	
	/** The executor service. */
	private ThreadPoolExecutor executorService;

	/** The state. */
	private States state;

	/** The queue. */
	Queue queue;

	/** The queue conn. */
	Connection queueConn;

	/** The consumer. */
	MessageConsumer consumer;

	/** The params. */
	private Map<String, String> params;

	/**
	 * Instantiates a new reader queue recycle master thread.
	 */
	public ReaderQueueRecycleMasterThread() {
		try {
			initialize();
		} catch (Exception e) {
			state = States.SHUTTINGDOWN;
			LOGGER.error("Unable to initialize : " + e.getMessage(), e);
		}
	}


	/**
	 * Initialize.
	 */
	public void initialize() {
		BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<Runnable>(1);
		LOGGER.info("workingQueue correctly");
		executorService = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MILLISECONDS, workingQueue);
		// Starting master thread
		state = States.STARTED;
		LOGGER.info("Iinitialize Finalized.");
	}

	
	/**
	 * Shutdown.
	 */
	public void shutdown() {
		state = States.SHUTTINGDOWN;
		executorService.shutdownNow();
	}

	/**
	 * Startup queue connection.
	 *
	 * @return true, if successful
	 */
	private boolean startupQueueConnection() {
		if (this.queueConn != null) {
			try {
				this.queueConn.close();
				this.queueConn = null;
			} catch (JMSException e2) {
				LOGGER.error("Queue connection could not be closed while performing cleanup.");
			}
		}
		InitialContext ctx = null;
		QueueConnectionFactory qcf = null;
		Hashtable<String, String> properties = new Hashtable<String, String>();
		properties.put("java.naming.factory.initial", params.get("INITITAL_CTX_CF"));
		properties.put("java.naming.provider.url", params.get("T3_QUEUE_READER_RECYCLE"));
		try {
			ctx = new InitialContext(properties);
			qcf = (QueueConnectionFactory) ctx.lookup(params.get("CONNECTION_QUEUE_CF_QRR"));
			this.queue = (Queue) ctx.lookup(params.get("CONNECTION_QUEUE_DQ_QRR"));
			this.queueConn = (Connection) qcf.createQueueConnection();
			this.queueConn.start();
			ctx.close();
			return true;
		} catch (NamingException e) {
			LOGGER.error("JNDI-related problems while trying to retrieve Queue/QueueConnectionFactory.");
			return false;
		} catch (JMSException e) {
			LOGGER.error("Problems while trying to establish connection to queue.");
			return false;
		}
	}

	
	/**
	 * Run.
	 */
	@Override
	public void run() {

		getConnection();
		startupQueueConnection();
		
		while (state == States.STARTED) {
			
			long startTime = 0;	
			/*try {
				Session session = this.queueConn.createSession(false, 1);
				this.consumer = session.createConsumer((Destination) this.queue);
			} catch (JMSException e) {
				LOGGER.error("Error ocurred while attempting creation of queue session.");
				return;
			}*/

			try {
				
				TextMessage message = (TextMessage) this.consumer.receive();
				String mensaje = message.getText();

				startTime = sendToCPE(startTime, mensaje);

			} catch (Exception error) {
				LOGGER.error(QueueConstantListener.NAME_LISTENER + this.getClass().getName()
						+ QueueConstantListener.MESSAGE_ERROR_PROCESS, error);
				NewRelicImpl.addNewRelicError(error);
			} finally {
				long endTime = System.nanoTime(); // Se guarda el tiempo final del proceso.
				long duration = (endTime - startTime); // Se calcula el tiempo que tomo procesar los datos.
				long timeDuration = duration / 1000000;
				NewRelicImpl.addNewRelicMetric("QueueReaderReycle", timeDuration); // Se manda la informacion de
																							// la duracion del proceso a
																							// NewRelic como metrica.
				startTime = 0;

			}
			state = States.SHUTTINGDOWN;
		}
		executorService.shutdown();
	}


	/**
	 * Send to CPE.
	 *
	 * @param startTime the start time
	 * @param mensaje the mensaje
	 * @return the long
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws MalformedURLException the malformed URL exception
	 */
	public long sendToCPE(long startTime, String mensaje) throws IOException, MalformedURLException {
		
		if (mensaje != null && !mensaje.equals("")) {
			startTime = System.nanoTime();
			LOGGER.info("Mensaje Leido: " + mensaje);
			
			Gson gson = new Gson();
			NotifyMessageDTO notifyMessage = gson.fromJson(mensaje, NotifyMessageDTO.class);
			
			ReadFilesConfig readConfig = new ReadFilesConfig();
			final ConfigEventDTO configEvent = readConfig.readConfigEvent();
			DetailEventDTO detailEvent = readConfig.getDetailEvent(configEvent, notifyMessage.getEventType());

			CPEComplexServiceProvider service = new CPEComplexServiceProvider(detailEvent.getEndpoint());
			OrderResponse response = service
					.excuteComplexOrder(createRequest(notifyMessage, detailEvent.getComment()));
			
			if (response != null) {
				LOGGER.info(QueueConstantListener.NAME_LISTENER + QueueConstantListener.TRANSACTION_ID
						+ response.getTransactionID() + QueueConstantListener.CODE + response.getCode()
						+ QueueConstantListener.DESCRIPTION + response.getMessage());
				
				if (response.getCode() != 0) {
					NewRelicImpl.addNewRelicErrorMessage("QueueReaderReycle " + response.getMessage());
				}
			}
		}
		return startTime;
	}

	/**
	 * Creates the request.
	 *
	 * @param notifyMessage the notify message
	 * @param comment the comment
	 * @return the complex order request
	 */
	private ComplexOrderRequest createRequest(final NotifyMessageDTO notifyMessage, final String comment) {

		ComplexOrderRequest request = new ComplexOrderRequest();
		request.setChannelId(Integer.valueOf(notifyMessage.getChannelId()));
		request.setComment(comment);
		request.setExternalTransacionId(UUID.randomUUID().toString());
		OrderRequestDetail orderRequestDetail = new OrderRequestDetail();
		orderRequestDetail.setProductId(notifyMessage.getProductId());
		orderRequestDetail.setSubscriberId(notifyMessage.getSubscriberId());
		orderRequestDetail.setQuantity(1);

		if (notifyMessage.getOrderType().equalsIgnoreCase((EnumOrderType.ACTIVATE.name()))) {
			orderRequestDetail.setOrderType(EnumOrderType.ACTIVATE);
		} else if (notifyMessage.getOrderType().equalsIgnoreCase((EnumOrderType.DEACTIVATE.name()))) {
			orderRequestDetail.setOrderType(EnumOrderType.DEACTIVATE);
		} else if (notifyMessage.getOrderType().equalsIgnoreCase((EnumOrderType.LOAN.name()))) {
			orderRequestDetail.setOrderType(EnumOrderType.LOAN);
		} else if (notifyMessage.getOrderType().equalsIgnoreCase((EnumOrderType.PURCHASE.name()))) {
			orderRequestDetail.setOrderType(EnumOrderType.PURCHASE);
		} else if (notifyMessage.getOrderType().equalsIgnoreCase((EnumOrderType.SELL.name()))) {
			orderRequestDetail.setOrderType(EnumOrderType.SELL);
		} else if (notifyMessage.getOrderType().equalsIgnoreCase((EnumOrderType.TRANSFER.name()))) {
			orderRequestDetail.setOrderType(EnumOrderType.TRANSFER);
		}
		AdditionalParameters addParameters = new AdditionalParameters();
		getAddParamsData(notifyMessage, addParameters);
		
		orderRequestDetail.setAdditionalParameters(addParameters);
		request.getOrderRequestDetail().add(orderRequestDetail);

		return request;
	}


	/**
	 * Gets the adds the params data.
	 *
	 * @param notifyMessage the notify message
	 * @param addParameters the add parameters
	 * @return the adds the params data
	 */
	private void getAddParamsData(final NotifyMessageDTO notifyMessage, AdditionalParameters addParameters) {
		
		Parameter parameter;
		for (int count = 0; count <= notifyMessage.getAdditionalsParams().size() - 2; count++) {
			if (count == 0) {
				continue;
			} else if (count == 1) {
				parameter = new Parameter();
				parameter.setKey(params.get("SUBEVENT_KEY"));
				parameter.setValue(notifyMessage.getAdditionalsParams().get(count).getAttribute());
				addParameters.getParameter().add(parameter);
			} else {
				parameter = new Parameter();
				parameter.setKey(notifyMessage.getAdditionalsParams().get(count).getAttribute());
				try {
					if (!notifyMessage.getAdditionalsParams().get(count).getValue().equals("")) {
						parameter.setValue(notifyMessage.getAdditionalsParams().get(count).getValue());
					} else {
						parameter.setValue(params.get("NOT_AVAILABLE"));
					}
				} catch (Exception e) {
					parameter.setValue(params.get("NOT_AVAILABLE"));
				}
				addParameters.getParameter().add(parameter);
			}
		}
	}

	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	@SuppressWarnings("unchecked")
	private void getConnection() {
		OcepManager manager = null;
		try {
			ServiceSessionEJBLocal<OcepManager> serviceSession = ServiceSessionEJB.getInstance();
			manager = serviceSession.getSessionDataSource(OcepManager.class,
					QueueConstantListener.DATASOURCE_CPE);
			params = manager.listAllParam();
		} catch (PersistenceException e) {
			LOGGER.error(QueueConstantListener.ERROR_CONNECTION + e.getMessage(), e);
		} finally {
			if (manager != null) {
				try {
					manager.close();
				} catch (PersistenceException e) {
					LOGGER.error(QueueConstantListener.COULD_NOT_CLOSE);
				}
			}
		}
	}
	
	/**
	 * Sets the params.
	 *
	 * @param params the params
	 */
	public void setParams(Map<String, String> params) {
		this.params = params;
	}

}
