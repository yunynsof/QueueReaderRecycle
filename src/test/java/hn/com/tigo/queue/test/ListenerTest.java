package hn.com.tigo.queue.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import hn.com.tigo.queue.listener.ReaderQueueRecycleMasterThread;


public class ListenerTest {


	@Test
	public void test() {

		ReaderQueueRecycleMasterThread thread = new ReaderQueueRecycleMasterThread();
		Map<String, String> params = new HashMap<String, String>();
		params.put("SUBEVENT_KEY", "SUBEVENT");
		params.put("NOT_AVAILABLE", "n/a");
		params.put("INITITAL_CTX_CF", "weblogic.jndi.WLInitialContextFactory");
		params.put("T3_QUEUE_READER_RECYCLE", "t3://192.168.159.46:7004");
		params.put("CONNECTION_QUEUE_DQ_QRR", "jms/processorRecycleDQ");
		params.put("CONNECTION_QUEUE_CF_QRR", "jms/processorRecycleCF");
		thread.setParams(params);
		thread.run();

		long startTime = 0;
		String mensaje = "{\"eventType\":\"RECICLAJE\",\"channelId\":\"31\",\"orderType\":\"ACTIVATE\",\"productId\":9002206,\"subscriberId\":\"94517500\",\"date\":\"20220823\",\"transactionId\":\"50df1c20-33f5-4119-afa1-4c3fb49b70b7\",\"additionalsParams\":[{\"attribute\":\"EVENT\",\"value\":\"SEGUROS\"},{\"attribute\":\"SUBEVENT\",\"value\":\"SEGUROS\"},{\"attribute\":\"MSISDN\",\"value\":\"94517500\"},{\"attribute\":\"CUENTA_CLIENTE\",\"value\":\"0801198716839CCHN\"},{\"attribute\":\"CUENTA_FACTURACION\",\"value\":\"8000066364\"},{\"attribute\":\"ID_PEDIDO_VENTA\",\"value\":\"1521895736507599298\"},{\"attribute\":\"NUMERO_FACTURA\",\"value\":\"3330\"},{\"attribute\":\"FECHAEMISION\",\"value\":\"20200213\"},{\"attribute\":\"IMEI\",\"value\":\"869175040414278\"},{\"attribute\":\"TOTAL_FACTURADO\",\"value\":\"100.00\"},{\"attribute\":\"TIPO_FACTURA\",\"value\":\"FC2\"},{\"attribute\":\"USUARIO\",\"value\":\"HCORDERO\"},{\"attribute\":\"MOTIVO\",\"value\":\"PAGO\"},{\"attribute\":\"TEST_QA4\",\"value\":\"\"},{\"attribute\":\"123581321\",\"value\":\"\"}]}";
		String mensaje1 = "{\"eventType\":\"RECICLAJE\",\"channelId\":\"31\",\"orderType\":\"PURCHASE\",\"productId\":774,\"subscriberId\":\"94517500\",\"date\":\"20220824\",\"transactionId\":\"9f22ac4c-3c9e-4859-ab56-e1a54a9c728b\",\"additionalsParams\":[{\"attribute\":\"EVENT\",\"value\":\"FACTURACION\"},{\"attribute\":\"SUBEVENT\",\"value\":\"FACTURACION\"},{\"attribute\":\"MSISDN\",\"value\":\"94517500\"},{\"attribute\":\"CUENTA_CLIENTE\",\"value\":\"0801198716839CCHN\"},{\"attribute\":\"CUENTA_FACTURACION\",\"value\":\"8000066364\"},{\"attribute\":\"ID_PEDIDO_VENTA\",\"value\":\"1521895736507599298\"},{\"attribute\":\"NUMERO_FACTURA\",\"value\":\"3330\"},{\"attribute\":\"FECHAEMISION\",\"value\":\"20200213\"},{\"attribute\":\"IMEI\",\"value\":\"869175040414278\"},{\"attribute\":\"TOTAL_FACTURADO\",\"value\":\"100.00\"},{\"attribute\":\"TIPO_FACTURA\",\"value\":\"FC2\"},{\"attribute\":\"USUARIO\",\"value\":\"HCORDERO\"},{\"attribute\":\"MOTIVO\",\"value\":\"PAGO\"},{\"attribute\":\"TEST_QA\",\"value\":\"\"},{\"attribute\":\"123432\",\"value\":\"\"}]}";
		String mensaje2 = "{\"eventType\":\"RECICLAJE\",\"channelId\":\"31\",\"orderType\":\"DEACTIVATE\",\"productId\":9002206,\"subscriberId\":\"94517500\",\"date\":\"20220823\",\"transactionId\":\"50df1c20-33f5-4119-afa1-4c3fb49b70b7\",\"additionalsParams\":[{\"attribute\":\"EVENT\",\"value\":\"SEGUROS\"},{\"attribute\":\"SUBEVENT\",\"value\":\"SEGUROS\"},{\"attribute\":\"MSISDN\",\"value\":\"94517500\"},{\"attribute\":\"CUENTA_CLIENTE\",\"value\":\"0801198716839CCHN\"},{\"attribute\":\"CUENTA_FACTURACION\",\"value\":\"8000066364\"},{\"attribute\":\"ID_PEDIDO_VENTA\",\"value\":\"1521895736507599298\"},{\"attribute\":\"NUMERO_FACTURA\",\"value\":\"3330\"},{\"attribute\":\"FECHAEMISION\",\"value\":\"20200213\"},{\"attribute\":\"IMEI\",\"value\":\"869175040414278\"},{\"attribute\":\"TOTAL_FACTURADO\",\"value\":\"100.00\"},{\"attribute\":\"TIPO_FACTURA\",\"value\":\"FC2\"},{\"attribute\":\"USUARIO\",\"value\":\"HCORDERO\"},{\"attribute\":\"MOTIVO\",\"value\":\"PAGO\"},{\"attribute\":\"TEST_QA4\",\"value\":\"\"},{\"attribute\":\"123581321\",\"value\":\"\"}]}";
		String mensaje3 = "{\"eventType\":\"RECICLAJE\",\"channelId\":\"31\",\"orderType\":\"LOAN\",\"productId\":774,\"subscriberId\":\"94517500\",\"date\":\"20220824\",\"transactionId\":\"9f22ac4c-3c9e-4859-ab56-e1a54a9c728b\",\"additionalsParams\":[{\"attribute\":\"EVENT\",\"value\":\"FACTURACION\"},{\"attribute\":\"SUBEVENT\",\"value\":\"FACTURACION\"},{\"attribute\":\"MSISDN\",\"value\":\"94517500\"},{\"attribute\":\"CUENTA_CLIENTE\",\"value\":\"0801198716839CCHN\"},{\"attribute\":\"CUENTA_FACTURACION\",\"value\":\"8000066364\"},{\"attribute\":\"ID_PEDIDO_VENTA\",\"value\":\"1521895736507599298\"},{\"attribute\":\"NUMERO_FACTURA\",\"value\":\"3330\"},{\"attribute\":\"FECHAEMISION\",\"value\":\"20200213\"},{\"attribute\":\"IMEI\",\"value\":\"869175040414278\"},{\"attribute\":\"TOTAL_FACTURADO\",\"value\":\"100.00\"},{\"attribute\":\"TIPO_FACTURA\",\"value\":\"FC2\"},{\"attribute\":\"USUARIO\",\"value\":\"HCORDERO\"},{\"attribute\":\"MOTIVO\",\"value\":\"PAGO\"},{\"attribute\":\"TEST_QA\",\"value\":\"\"},{\"attribute\":\"123432\",\"value\":\"\"}]}";
		String mensaje4 = "{\"eventType\":\"RECICLAJE\",\"channelId\":\"31\",\"orderType\":\"SELL\",\"productId\":9002206,\"subscriberId\":\"94517500\",\"date\":\"20220823\",\"transactionId\":\"50df1c20-33f5-4119-afa1-4c3fb49b70b7\",\"additionalsParams\":[{\"attribute\":\"EVENT\",\"value\":\"SEGUROS\"},{\"attribute\":\"SUBEVENT\",\"value\":\"SEGUROS\"},{\"attribute\":\"MSISDN\",\"value\":\"94517500\"},{\"attribute\":\"CUENTA_CLIENTE\",\"value\":\"0801198716839CCHN\"},{\"attribute\":\"CUENTA_FACTURACION\",\"value\":\"8000066364\"},{\"attribute\":\"ID_PEDIDO_VENTA\",\"value\":\"1521895736507599298\"},{\"attribute\":\"NUMERO_FACTURA\",\"value\":\"3330\"},{\"attribute\":\"FECHAEMISION\",\"value\":\"20200213\"},{\"attribute\":\"IMEI\",\"value\":\"869175040414278\"},{\"attribute\":\"TOTAL_FACTURADO\",\"value\":\"100.00\"},{\"attribute\":\"TIPO_FACTURA\",\"value\":\"FC2\"},{\"attribute\":\"USUARIO\",\"value\":\"HCORDERO\"},{\"attribute\":\"MOTIVO\",\"value\":\"PAGO\"},{\"attribute\":\"TEST_QA4\",\"value\":\"\"},{\"attribute\":\"123581321\",\"value\":\"\"}]}";
		String mensaje5 = "{\"eventType\":\"RECICLAJE\",\"channelId\":\"31\",\"orderType\":\"TRANSFER\",\"productId\":774,\"subscriberId\":\"94517500\",\"date\":\"20220824\",\"transactionId\":\"9f22ac4c-3c9e-4859-ab56-e1a54a9c728b\",\"additionalsParams\":[{\"attribute\":\"EVENT\",\"value\":\"FACTURACION\"},{\"attribute\":\"SUBEVENT\",\"value\":\"FACTURACION\"},{\"attribute\":\"MSISDN\",\"value\":\"94517500\"},{\"attribute\":\"CUENTA_CLIENTE\",\"value\":\"0801198716839CCHN\"},{\"attribute\":\"CUENTA_FACTURACION\",\"value\":\"8000066364\"},{\"attribute\":\"ID_PEDIDO_VENTA\",\"value\":\"1521895736507599298\"},{\"attribute\":\"NUMERO_FACTURA\",\"value\":\"3330\"},{\"attribute\":\"FECHAEMISION\",\"value\":\"20200213\"},{\"attribute\":\"IMEI\",\"value\":\"869175040414278\"},{\"attribute\":\"TOTAL_FACTURADO\",\"value\":\"100.00\"},{\"attribute\":\"TIPO_FACTURA\",\"value\":\"FC2\"},{\"attribute\":\"USUARIO\",\"value\":\"HCORDERO\"},{\"attribute\":\"MOTIVO\",\"value\":\"PAGO\"},{\"attribute\":\"TEST_QA\",\"value\":\"\"},{\"attribute\":\"123432\",\"value\":\"\"}]}";
		
		
		List<String> list = new ArrayList<String>();
		list.add(mensaje);
		list.add(mensaje1);
		list.add(mensaje2);
		list.add(mensaje3);
		list.add(mensaje4);
		list.add(mensaje5);
		list.add("");
		
		for (int i = 0; i < list.size(); i++) {
			try {
				thread.sendToCPE(startTime, list.get(i));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
