
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
    public String addressLine1; // (1, 200)

    @JsonProperty("addressLine2")
    public String addressLine2; // (0, 200)

    @JsonProperty("city")
    public String city;

    @JsonProperty("state")
    public String state; // (0, 2)

    @JsonProperty("postalCode")
    public String postalCode;

    @JsonProperty("country")
    public String country;

    @JsonProperty("region")
    public String region;

    @JsonProperty("firstName")
    public String firstName;

    @JsonProperty("lastName")
    public String lastName;

    @JsonProperty("phoneNumber")
    public String phoneNumber;

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
