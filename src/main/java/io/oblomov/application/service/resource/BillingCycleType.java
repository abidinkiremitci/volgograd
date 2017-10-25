package io.oblomov.application.service.resource;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum //TODO: Check XML Enum
public enum BillingCycleType {
    UNKNOWN (0,	"Initializer."),
    MONTHLY (1,	"Indicates that the partner will be charged monthly."),
    ANNUAL	(2,	"Indicates that the partner will be charged annually."),
    NONE	(3,	"Indicates that the partner will not be charged. This value may be used for trial offers.");

    int value;
    String description;

    BillingCycleType(int value, String description) {
        this.value = value;
        this.description = description;
    }
}
