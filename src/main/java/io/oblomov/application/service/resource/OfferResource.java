
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
        "acquisitionType",
        "actions",
        "attributes",
        "billing",
        "category",
        "conversionTargetOffers",
        "country",
        "description",
        "hasAddOns",
        "id",
        "isAddOn",
        "isAutoRenewable",
        "isAvailableForPurchase",
        "isInternal",
        "isMicrosoftProduct",
        "isTrial",
        "links",
        "locale",
        "maximumQuantity",
        "minimumQuantity",
        "name",
        "partnerQualifications",
        "prerequisiteOffers",
        "product",
        "rank",
        "reselleeQualifications",
        "resellerQualifications",
        "salesGroupId",
        "supportedBillingCycles",
        "supportedCatalogTypes",
        "unitType",
        "upgradeTargetOffers",
        "uri"
})
@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType
public class OfferResource {

    @JsonProperty("acquisitionType")
    private String acquisitionType;

    @JsonProperty("actions")
    private List<String> actions = null;

    @JsonProperty("attributes")
    private ResourceAttributes attributes;

    @JsonProperty("billing")
    private String billing;

    @JsonProperty("category")
    private OfferCategoryResource category;

    @JsonProperty("conversionTargetOffers")
    private List<Object> conversionTargetOffers = null;

    @JsonProperty("country")
    private String country;

    @JsonProperty("description")
    private String description;

    @JsonProperty("hasAddOns")
    private Boolean hasAddOns;

    @JsonProperty("id")
    private String id;

    @JsonProperty("isAddOn")
    private Boolean isAddOn;

    @JsonProperty("isAutoRenewable")
    private Boolean isAutoRenewable;

    @JsonProperty("isAvailableForPurchase")
    private Boolean isAvailableForPurchase;

    @JsonProperty("isInternal")
    private Boolean isInternal;

    @JsonProperty("isMicrosoftProduct")
    private Boolean isMicrosoftProduct;

    @JsonProperty("isTrial")
    private Boolean isTrial;

    @JsonProperty("links")
    private OfferLinks links;

    @JsonProperty("locale")
    private String locale;

    @JsonProperty("maximumQuantity")
    private Integer maximumQuantity;

    @JsonProperty("minimumQuantity")
    private Integer minimumQuantity;

    @JsonProperty("name")
    private String name;

    @JsonProperty("partnerQualifications")
    private List<String> partnerQualifications = null;

    @JsonProperty("prerequisiteOffers")
    private List<String> prerequisiteOffers = null;

    @JsonProperty("product")
    private ProductResource product;

    @JsonProperty("rank")
    private Integer rank;

    @JsonProperty("reselleeQualifications")
    private List<Object> reselleeQualifications = null;

    @JsonProperty("resellerQualifications")
    private List<Object> resellerQualifications = null;

    @JsonProperty("salesGroupId")
    private String salesGroupId;

    @JsonProperty("supportedBillingCycles")
    private List<String> supportedBillingCycles = null;

    @JsonProperty("supportedCatalogTypes")
    private List<String> supportedCatalogTypes = null;

    @JsonProperty("unitType")
    private String unitType;

    @JsonProperty("upgradeTargetOffers")
    private List<String> upgradeTargetOffers = null;

    @JsonProperty("uri")
    private String uri;

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
    public OfferResource(String acquisitionType, List<String> actions, ResourceAttributes attributes, String billing,
                         OfferCategoryResource category, List<Object> conversionTargetOffers, String country,
                         String description, Boolean hasAddOns, String id, Boolean isAddOn, Boolean isAutoRenewable,
                         Boolean isAvailableForPurchase, Boolean isInternal, Boolean isMicrosoftProduct, Boolean isTrial,
                         OfferLinks links, String locale, Integer maximumQuantity, Integer minimumQuantity,
                         String name, List<String> partnerQualifications, List<String> prerequisiteOffers,
                         ProductResource product, Integer rank, List<Object> reselleeQualifications,
                         List<Object> resellerQualifications, String salesGroupId, List<String> supportedBillingCycles,
                         List<String> supportedCatalogTypes, String unitType, List<String> upgradeTargetOffers,
                         String uri, Map<String, Object> additionalProperties) {
        this.acquisitionType = acquisitionType;
        this.actions = actions;
        this.attributes = attributes;
        this.billing = billing;
        this.category = category;
        this.conversionTargetOffers = conversionTargetOffers;
        this.country = country;
        this.description = description;
        this.hasAddOns = hasAddOns;
        this.id = id;
        this.isAddOn = isAddOn;
        this.isAutoRenewable = isAutoRenewable;
        this.isAvailableForPurchase = isAvailableForPurchase;
        this.isInternal = isInternal;
        this.isMicrosoftProduct = isMicrosoftProduct;
        this.isTrial = isTrial;
        this.links = links;
        this.locale = locale;
        this.maximumQuantity = maximumQuantity;
        this.minimumQuantity = minimumQuantity;
        this.name = name;
        this.partnerQualifications = partnerQualifications;
        this.prerequisiteOffers = prerequisiteOffers;
        this.product = product;
        this.rank = rank;
        this.reselleeQualifications = reselleeQualifications;
        this.resellerQualifications = resellerQualifications;
        this.salesGroupId = salesGroupId;
        this.supportedBillingCycles = supportedBillingCycles;
        this.supportedCatalogTypes = supportedCatalogTypes;
        this.unitType = unitType;
        this.upgradeTargetOffers = upgradeTargetOffers;
        this.uri = uri;
        this.additionalProperties = additionalProperties;
    }
}
