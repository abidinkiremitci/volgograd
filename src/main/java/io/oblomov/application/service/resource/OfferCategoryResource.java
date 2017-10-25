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
        "attributes",
        "country",
        "id",
        "locale",
        "name",
        "rank"
})
@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType
public class OfferCategoryResource {

    @JsonProperty("attributes")
    private ResourceAttributes attributes;

    @JsonProperty("country")
    private String country;

    @JsonProperty("id")
    private String id;

    @JsonProperty("locale")
    private String locale;

    @JsonProperty("name")
    private String name;

    @JsonProperty("rank")
    private Integer rank;

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
    public OfferCategoryResource(ResourceAttributes attributes, String country, String id, String locale, String name,
                                 Integer rank, Map<String, Object> additionalProperties) {
        this.attributes = attributes;
        this.country = country;
        this.id = id;
        this.locale = locale;
        this.name = name;
        this.rank = rank;
        this.additionalProperties = additionalProperties;
    }
}
