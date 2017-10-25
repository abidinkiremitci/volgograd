
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
        "etag",
        "objectType"
})
@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType
public class ResourceAttributes {

    @JsonProperty("etag")
    private String etag;

    @JsonProperty("objectType")
    private String objectType;

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
    public ResourceAttributes(String etag, String objectType, Map<String, Object> additionalProperties) {
        this.etag = etag;
        this.objectType = objectType;
        this.additionalProperties = additionalProperties;
    }
}
