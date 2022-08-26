/**
 * 
 */
package hn.com.tigo.queue.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import com.tigo.josm.gateway.services.order.complexorderrequest.v1.ComplexOrderRequest;
import com.tigo.josm.gateway.services.order.orderresponse.v1.OrderResponse;

import hn.com.tigo.josm.gateway.services.gateway.ComplexOrder;
import hn.com.tigo.josm.gateway.services.gateway.ExecuteComplexOrderService;


/**
 * CPEComplexServiceProvider.
 *
 * @author Yuny Rene Rodriguez Perez {@literal<mailto: yrodriguez@hightech-corp.com />}
 * @version  1.0.0
 * @since 08-25-2022 10:19:53 PM 2022
 */
public class CPEComplexServiceProvider {
	
	
	/** The end point. */
	private String endPoint;
	
	/** The url. */
	private final URL url;
	
	/** The web service. */
	ExecuteComplexOrderService webService = null;
	
	/** The web service port. */
	ComplexOrder webServicePort = null;
	
	
	/**
	 * Instantiates a new CPE complex service provider.
	 *
	 * @param endPoint the end point
	 * @throws MalformedURLException the malformed URL exception
	 */
	public CPEComplexServiceProvider(String endPoint) throws MalformedURLException
	{
		this.endPoint = endPoint;
		this.url = new URL(endPoint + "?wsdl");
		webService = new ExecuteComplexOrderService(this.url);
		webServicePort = webService.getExecuteComplexOrderPort();
		Map <String, Object> ctx= ((BindingProvider)webServicePort).getRequestContext();
		ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);
	}
	
	
	/**
	 * Excute complex order.
	 *
	 * @param request the request
	 * @return the order response
	 */
	public OrderResponse excuteComplexOrder(final ComplexOrderRequest request) {
		return webServicePort.execute(request);
	}

	
	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public URL getUrl() {
		return url;
	}

	
	/**
	 * Gets the end point.
	 *
	 * @return the end point
	 */
	public String getEndPoint() {
		return endPoint;
	}

	
	/**
	 * Sets the end point.
	 *
	 * @param endPoint the new end point
	 */
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	
}
