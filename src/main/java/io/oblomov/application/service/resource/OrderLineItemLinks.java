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
        "subscription"
})
@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType
public class OrderLineItemLinks {

    @JsonProperty("subscription")
    ResourceLink subscription;

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
    public OrderLineItemLinks(ResourceLink subscription, Map<String, Object> additionalProperties) {
        this.subscription = subscription;
        this.additionalProperties = additionalProperties;
    }
}
