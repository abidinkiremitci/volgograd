
package io.oblomov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "identity",
        "is_test",
        "links",
        "object_type"
})
@lombok.Data
public class ResellerId {

    @JsonProperty("id")
    public String id;
    @JsonProperty("identity")
    public Identity identity;
    @JsonProperty("is_test")
    public Boolean isTest;
    @JsonProperty("links")
    public Links links;
    @JsonProperty("object_type")
    public String objectType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
