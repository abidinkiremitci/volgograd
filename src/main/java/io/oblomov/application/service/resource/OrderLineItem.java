package io.oblomov.application.service.resource;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "friendlyName",
        "lineItemNumber",
        "links",
        "offerId",
        "partnerIdOnRecord",
        "parentSubscriptionId",
        "quantity",
        "subscriptionId"
})
@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType
public class OrderLineItem {

    @JsonProperty("friendlyName")
    private Integer friendlyName;

    @JsonProperty("lineItemNumber")
    private Integer lineItemNumber;

    @JsonProperty("links")
    private OrderLineItemLinks links;

    @JsonProperty("offerId")
    private String offerId;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("partnerIdOnRecord")
    private String partnerIdOnRecord;

    @JsonProperty("parentSubscriptionId")
    private String parentSubscriptionId;

    @JsonProperty("subscriptionId")
    private String subscriptionId;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Builder
    public OrderLineItem(Integer friendlyName, Integer lineItemNumber, OrderLineItemLinks links, String offerId,
                         Integer quantity, String partnerIdOnRecord, String parentSubscriptionId, String subscriptionId,
                         Map<String, Object> additionalProperties) {
        this.friendlyName = friendlyName;
        this.lineItemNumber = lineItemNumber;
        this.links = links;
        this.offerId = offerId;
        this.quantity = quantity;
        this.partnerIdOnRecord = partnerIdOnRecord;
        this.parentSubscriptionId = parentSubscriptionId;
        this.subscriptionId = subscriptionId;
        this.additionalProperties = additionalProperties;
    }
}