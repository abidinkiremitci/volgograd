package io.oblomov.application.service.resource;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum //TODO: Check XML Enum
public enum ObjectType {
    ORDER("Order"),
    ORDER_LINE_ITEM("OrderLineItem"),
    SUPPORT_CONTACT("SupportContact"),
    NONE("None")
    ;

    String value;

    ObjectType(String value) {
        this.value = value;
    }
}
