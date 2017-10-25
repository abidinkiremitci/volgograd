
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
        "addressLine1",
        "addressLine2",
        "city",
        "state",
        "postalCode",
        "country",
        "region",
        "firstName",
        "lastName",
        "phoneNumber"
})
@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType
public class AddressResource {

    @JsonProperty("addressLine1")
    private String addressLine1; // (1, 200)

    @JsonProperty("addressLine2")
    private String addressLine2; // (0, 200)

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state; // (0, 2)

    @JsonProperty("postalCode")
    private String postalCode;

    @JsonProperty("country")
    private String country;

    @JsonProperty("region")
    private String region;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

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
    public AddressResource(String addressLine1, String addressLine2, String city, String state, String postalCode,
                           String country, String region, String firstName, String lastName, String phoneNumber,
                           Map<String, Object> additionalProperties) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.region = region;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.additionalProperties = additionalProperties;
    }
}
