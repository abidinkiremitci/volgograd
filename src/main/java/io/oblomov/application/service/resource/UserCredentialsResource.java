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
        "userName",
        "password"
})
@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType
public class UserCredentialsResource {

    @JsonProperty("userName")
    String userName;

    @JsonProperty("password")
    SecureStringResource password;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonAnyGetter
    private Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    private void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Builder
    public UserCredentialsResource(String userName, SecureStringResource password, Map<String, Object> additionalProperties) {
        this.userName = userName;
        this.password = password;
        this.additionalProperties = additionalProperties;
    }
}
