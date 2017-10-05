
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
        "id",
        "firstName",
        "lastName",
        "email",
        "culture",
        "language",
        "companyName",
        "defaultAddress",
        "links",
        "attributes"
})
@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType
public class BillingProfileResource {

    @JsonProperty("id")
    public String id;

    @JsonProperty("firstName")
    public String firstName;

    @JsonProperty("lastName")
    public String lastName;

    @JsonProperty("email")
    public String email;

    @JsonProperty("culture")
    public String culture;

    @JsonProperty("language")
    public String language;

    @JsonProperty("companyName")
    public String companyName;

    @JsonProperty("defaultAddress")
    public AddressResource defaultAddress;

    @JsonProperty("links")
    public ResourceLinks links;

    @JsonProperty("attributes")
    public ResourceAttributes attributes;

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
    public BillingProfileResource(String id, String firstName, String lastName, String email, String culture, String language,
                                  String companyName, AddressResource defaultAddress, ResourceLinks links, ResourceAttributes attributes,
                                  Map<String, Object> additionalProperties) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.culture = culture;
        this.language = language;
        this.companyName = companyName;
        this.defaultAddress = defaultAddress;
        this.links = links;
        this.attributes = attributes;
        this.additionalProperties = additionalProperties;
    }
}
