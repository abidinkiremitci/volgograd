
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
    private String id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("culture")
    private String culture;

    @JsonProperty("language")
    private String language;

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("defaultAddress")
    private AddressResource defaultAddress;

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
