
package com.tigo.josm.gateway.services.order.complexorderrequest.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.tigo.josm.gateway.services.order.additionalparameterdto.v1.AdditionalParameters;


/**
 * <p>Clase Java para OrderRequestDetail complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="OrderRequestDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subscriberId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="orderType" type="{http://tigo.com/josm/gateway/services/order/ComplexOrderRequest/V1}EnumOrderType"/>
 *         &lt;element name="additionalParameters" type="{http://tigo.com/josm/gateway/services/order/AdditionalParameterDTO/V1}AdditionalParameters" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderRequestDetail", propOrder = {
    "subscriberId",
    "productId",
    "quantity",
    "orderType",
    "additionalParameters"
})
public class OrderRequestDetail {

    @XmlElement(required = true)
    protected String subscriberId;
    protected long productId;
    protected int quantity;
    @XmlElement(required = true)
    protected EnumOrderType orderType;
    protected AdditionalParameters additionalParameters;

    /**
     * Obtiene el valor de la propiedad subscriberId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberId() {
        return subscriberId;
    }

    /**
     * Define el valor de la propiedad subscriberId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberId(String value) {
        this.subscriberId = value;
    }

    /**
     * Obtiene el valor de la propiedad productId.
     * 
     */
    public long getProductId() {
        return productId;
    }

    /**
     * Define el valor de la propiedad productId.
     * 
     */
    public void setProductId(long value) {
        this.productId = value;
    }

    /**
     * Obtiene el valor de la propiedad quantity.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Define el valor de la propiedad quantity.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

    /**
     * Obtiene el valor de la propiedad orderType.
     * 
     * @return
     *     possible object is
     *     {@link EnumOrderType }
     *     
     */
    public EnumOrderType getOrderType() {
        return orderType;
    }

    /**
     * Define el valor de la propiedad orderType.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumOrderType }
     *     
     */
    public void setOrderType(EnumOrderType value) {
        this.orderType = value;
    }

    /**
     * Obtiene el valor de la propiedad additionalParameters.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalParameters }
     *     
     */
    public AdditionalParameters getAdditionalParameters() {
        return additionalParameters;
    }

    /**
     * Define el valor de la propiedad additionalParameters.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalParameters }
     *     
     */
    public void setAdditionalParameters(AdditionalParameters value) {
        this.additionalParameters = value;
    }

}
