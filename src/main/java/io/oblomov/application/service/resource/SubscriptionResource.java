package io.oblomov.application.service.resource;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Id",
        "offerId",
        "offerName",
        "FriendlyName",
        "Quantity",
        "UnitType",
        "ParentSubscriptionId",
        "CreationDate",
        "EffectiveStartDate",
        "CommitmentEndDate",
        "Status",
        "AutoRenewEnabled",
        "BillingType",
        "PartnerId",
        "suspensionReasons",
        "ContractType",
        "Links",
        "OrderId",
        "Attributes"
})
@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType
public class SubscriptionResource {

    @JsonProperty("Id")
    private String id;
    
    @JsonProperty("OfferId")
    private String offerId;

    @JsonProperty("OfferName")
    private String offerName;

    @JsonProperty("FriendlyName")
    private String friendlyName;
    
    @JsonProperty("Quantity")
    private Integer quantity;
    
    @JsonProperty("UnitType")
    private String unitType;
    
    @JsonProperty("ParentSubscriptionId")
    private Object parentSubscriptionId;
    
    @JsonProperty("CreationDate")
    private String creationDate;
    
    @JsonProperty("EffectiveStartDate")
    private Date effectiveStartDate;
    
    @JsonProperty("CommitmentEndDate")
    private Date commitmentEndDate;
    
    @JsonProperty("Status")
    private String status;
    
    @JsonProperty("AutoRenewEnabled")
    private Boolean autoRenewEnabled;
    
    @JsonProperty("BillingType")
    private String billingType;
    
    @JsonProperty("PartnerId")
    private String partnerId;

    @JsonProperty("SuspensionReasons")
    private String suspensionReasons;
    
    @JsonProperty("ContractType")
    private String contractType;
    
    @JsonProperty("Links")
    private ResourceLinks links;
    
    @JsonProperty("OrderId")
    private String orderId;
    
    @JsonProperty("Attributes")
    private ResourceAttributes attributes;
    
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


}
