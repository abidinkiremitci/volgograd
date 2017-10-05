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
        "uri",
        "method",
        "headers"
})
@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType
public class ResourceLink {

    @JsonProperty("uri")
    public String uri;

    @JsonProperty("method")
    public String method;

    @JsonProperty("headers")
    public List<Object> headers = null;

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
    public ResourceLink(String uri, String method, List<Object> headers, Map<String, Object> additionalProperties) {
        this.uri = uri;
        this.method = method;
        this.headers = headers;
        this.additionalProperties = additionalProperties;
    }
}
