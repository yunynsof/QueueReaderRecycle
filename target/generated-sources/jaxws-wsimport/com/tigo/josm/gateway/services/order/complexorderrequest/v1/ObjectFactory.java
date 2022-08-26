
package com.tigo.josm.gateway.services.order.complexorderrequest.v1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.tigo.josm.gateway.services.order.orderresponse.v1.OrderResponse;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tigo.josm.gateway.services.order.complexorderrequest.v1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ComplexOrderRequestInstance_QNAME = new QName("http://tigo.com/josm/gateway/services/order/ComplexOrderRequest/V1", "complexOrderRequestInstance");
    private final static QName _ComplexProductResponseOrder_QNAME = new QName("http://tigo.com/josm/gateway/services/order/ComplexOrderRequest/V1", "complexProductResponseOrder");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tigo.josm.gateway.services.order.complexorderrequest.v1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ComplexOrderRequest }
     * 
     */
    public ComplexOrderRequest createComplexOrderRequest() {
        return new ComplexOrderRequest();
    }

    /**
     * Create an instance of {@link OrderRequestDetail }
     * 
     */
    public OrderRequestDetail createOrderRequestDetail() {
        return new OrderRequestDetail();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComplexOrderRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tigo.com/josm/gateway/services/order/ComplexOrderRequest/V1", name = "complexOrderRequestInstance")
    public JAXBElement<ComplexOrderRequest> createComplexOrderRequestInstance(ComplexOrderRequest value) {
        return new JAXBElement<ComplexOrderRequest>(_ComplexOrderRequestInstance_QNAME, ComplexOrderRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OrderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tigo.com/josm/gateway/services/order/ComplexOrderRequest/V1", name = "complexProductResponseOrder")
    public JAXBElement<OrderResponse> createComplexProductResponseOrder(OrderResponse value) {
        return new JAXBElement<OrderResponse>(_ComplexProductResponseOrder_QNAME, OrderResponse.class, null, value);
    }

}
