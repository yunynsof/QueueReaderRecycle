
package com.tigo.josm.gateway.services.order.complexorderrequest.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EnumOrderType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="EnumOrderType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ACTIVATE"/>
 *     &lt;enumeration value="DEACTIVATE"/>
 *     &lt;enumeration value="LOAN"/>
 *     &lt;enumeration value="PURCHASE"/>
 *     &lt;enumeration value="SELL"/>
 *     &lt;enumeration value="TRANSFER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnumOrderType")
@XmlEnum
public enum EnumOrderType {

    ACTIVATE,
    DEACTIVATE,
    LOAN,
    PURCHASE,
    SELL,
    TRANSFER;

    public String value() {
        return name();
    }

    public static EnumOrderType fromValue(String v) {
        return valueOf(v);
    }

}
