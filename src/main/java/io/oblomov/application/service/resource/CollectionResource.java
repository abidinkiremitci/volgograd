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
        "totalCount",
        "items",
        "links",
        "attributes"
})
@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType
public class CollectionResource<T> {

    @JsonProperty("totalCount")
    private int totalCount;

    @JsonProperty("items")
    private List<T> items;

    @JsonProperty("links")
    private ResourceLinks links;

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
    public CollectionResource(int totalCount, List<T> items, ResourceLinks links,
                              ResourceAttributes attributes, Map<String, Object> additionalProperties) {
        this.totalCount = totalCount;
        this.items = items;
        this.links = links;
        this.attributes = attributes;
        this.additionalProperties = additionalProperties;
    }
}
