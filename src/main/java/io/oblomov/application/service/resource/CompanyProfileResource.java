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
        "tenantId",
        "domain",
        "companyName",
        "links",
        "attributes"
})
@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType
public class CompanyProfileResource {

    @JsonProperty("tenantId")
    public String tenantId;

    @JsonProperty("domain")
    public String domain;

    @JsonProperty("companyName")
    public String companyName;

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
    public CompanyProfileResource(String tenantId, String domain, String companyName, ResourceLinks links, ResourceAttributes attributes, Map<String, Object> additionalProperties) {
        this.tenantId = tenantId;
        this.domain = domain;
        this.companyName = companyName;
        this.links = links;
        this.attributes = attributes;
        this.additionalProperties = additionalProperties;
    }
}