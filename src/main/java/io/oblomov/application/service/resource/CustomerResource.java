package io.oblomov.application.service.resource;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "commerceId",
        "companyProfile",
        "billingProfile",
        "relationshipToPartner",
        "allowDelegatedAccess",
        "userCredentials",
        "customDomains",
        "associatedPartnerId",
        "links",
        "attributes"
})
@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType
public class CustomerResource {

    @JsonProperty("id")
    private String id;

    @JsonProperty("commerceId")
    private String commerceId;

    @JsonProperty("companyProfile")
    private CompanyProfileResource companyProfile;

    @JsonProperty("billingProfile")
    private BillingProfileResource billingProfile;

    @JsonProperty("relationshipToPartner")
    private String relationshipToPartner;

    @JsonProperty("allowDelegatedAccess")
    private boolean allowDelegatedAccess;

    @JsonProperty("userCredentials")
    private UserCredentialsResource userCredentials;

    @JsonProperty("customDomains")
    private List<String> customDomains = new ArrayList();

    @JsonProperty("associatedPartnerId")
    private String associatedPartnerId;

    @JsonProperty("links")
    private ResourceLinks links;

    @JsonProperty("attributes")
    private ResourceAttributes attributes;

    @JsonIgnore
    public  Map<String, Object> additionalProperties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    private void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Builder
    public CustomerResource(String id, String commerceId, CompanyProfileResource companyProfile, BillingProfileResource billingProfile,
                            String relationshipToPartner, boolean allowDelegatedAccess, UserCredentialsResource userCredentials,
                            List<String> customDomains, String associatedPartnerId, ResourceLinks links,
                            ResourceAttributes attributes, Map<String, Object> additionalProperties) {
        this.id = id;
        this.commerceId = commerceId;
        this.companyProfile = companyProfile;
        this.billingProfile = billingProfile;
        this.relationshipToPartner = relationshipToPartner;
        this.allowDelegatedAccess = allowDelegatedAccess;
        this.userCredentials = userCredentials;
        this.customDomains = customDomains;
        this.associatedPartnerId = associatedPartnerId;
        this.links = links;
        this.attributes = attributes;
        this.additionalProperties = additionalProperties;
    }
}
