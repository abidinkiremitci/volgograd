package io.oblomov.application.service.resource;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "attributes",
        "billingCycle",
        "creationDate",
        "id",
        "lineItems",
        "links",
        "referenceCustomerId"
})
@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType
public class OrderResource {

    @JsonProperty("attributes")
    private ResourceAttributes attributes;

    @JsonProperty("billingCycle")
    private String billingCycle;

    @JsonProperty("creationDate")
    private String creationDate;

    @JsonProperty("id")
    private String id;

    @JsonProperty("lineItems")
    private List<OrderLineItem> lineItems = null;

    @JsonProperty("links")
    private ResourceLinks links;

    @JsonProperty("referenceCustomerId")
    private String referenceCustomerId;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Builder
    public OrderResource(ResourceAttributes attributes, String billingCycle, String creationDate, String id,
                         List<OrderLineItem> lineItems, ResourceLinks links, String referenceCustomerId,
                         Map<String, Object> additionalProperties) {
        this.attributes = attributes;
        this.billingCycle = billingCycle;
        this.creationDate = creationDate;
        this.id = id;
        this.lineItems = lineItems;
        this.links = links;
        this.referenceCustomerId = referenceCustomerId;
        this.additionalProperties = additionalProperties;
    }
}
