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
        "self",
        "next",
        "previous",
        "attributes"
})
@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType
public class ResourceLinks {

    @JsonProperty("self")
    ResourceLink self;

    @JsonProperty("next")
    ResourceLink next;

    @JsonProperty("previous")
    ResourceLink previous;

    @JsonProperty("attributes")
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

    @Builder
    public ResourceLinks(ResourceLink self, ResourceLink next, ResourceLink previous, ResourceAttributes attributes, Map<String, Object> additionalProperties) {
        this.self = self;
        this.next = next;
        this.previous = previous;
        this.attributes = attributes;
        this.additionalProperties = additionalProperties;
    }
}
