package io.oblomov.application.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.oblomov.application.service.resource.*;
import io.oblomov.infra.exception.GeneralException;
import io.oblomov.infra.util.MessagePlaceHolderConverter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("microsoftResellerService")
public class MicrosoftResellerServiceImpl implements MicrosoftResellerService {

    @Autowired
    private OAuth2RestTemplate microsoftResellerRestTemplate;

    @Setter
    @Value("${config.client.baseUri}")
    String baseUri;

    final static String searchCustomerUri = "/v1/customers?size=${size}&filter=${filter}"; // size=int, filter=UrlEncode({"Field":"Domain","Value":"umuttest","Operator":"starts_with"})
    final static String createCustomerUri = "/v1/customers";
    final static String offerAvailibilityCheckUri = "/v1/offers?country=${country_id}";
    final static String customerOrder = "/v1/customers/${customer_id}/orders";
    final static String customerSubscription = "/v1/customers/${customer_id}/subscription";

    @Override
    public List<CustomerResource> searchCustomerByDomain(String domain) {
        String methodName = "searchCustomerByDomain";
        try {
            String filterForSearchCustomer = createFilterForSearchCustomer(domain);
            URI uri = createUriForSearchCustomer(20, filterForSearchCustomer);

            MicrosoftResellerLoggerUtil.debugServiceCall(methodName,uri.toString());

            ResponseEntity<CollectionResource<CustomerResource>> responseEntity = microsoftResellerRestTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<CollectionResource<CustomerResource>>() {});

            MicrosoftResellerLoggerUtil.debugServiceStatus(methodName,uri.toString(),responseEntity.getStatusCode().value());

            return Lists.newArrayList(responseEntity.getBody().getItems());
        } catch (HttpClientErrorException exception) {
            MicrosoftResellerLoggerUtil.debugServiceError(methodName,exception);
            MicrosoftResellerLoggerUtil.debug(methodName,exception.getResponseBodyAsString());
            throw new GeneralException(exception.getResponseBodyAsString());
        } catch (Exception exception) {
            MicrosoftResellerLoggerUtil.debugServiceError(methodName,exception);
            throw new GeneralException(exception.getMessage());
        }
    }

    private URI createUriForSearchCustomer(int size, String filter) throws URISyntaxException {
        Map<String, String> placeHolder = new HashMap<>();
        placeHolder.put("size", String.valueOf(size));
        placeHolder.put("filter", filter);
        String uri = MessagePlaceHolderConverter.replaceVariablesInMessageWithValues(this.searchCustomerUri, placeHolder);
        return new URI(new StringBuilder().append(this.baseUri).append(uri).toString());
    }

    private String createFilterForSearchCustomer(String domainValue) throws IOException {
        Filter filter = Filter.builder()
                .field("Domain")
                .value(domainValue)
                .operator("starts_with")
                .build();
        return URLEncoder.encode(filter.toJson(),"UTF-8");
    }

    @Override
    public CustomerResource createCustomer(CustomerResource customerResource) {
        String methodName = "createCustomer";
        try {
            URI uri = new URI(this.baseUri + this.createCustomerUri);
            MicrosoftResellerLoggerUtil.debugServiceCall(methodName, uri.toString());

            ResponseEntity<CustomerResource> responseEntity = microsoftResellerRestTemplate.postForEntity(uri, customerResource, CustomerResource.class);

            MicrosoftResellerLoggerUtil.debugServiceStatus(methodName, uri.toString(), responseEntity.getStatusCode().value());

            return responseEntity.getBody();
        } catch (HttpClientErrorException exception) {
            MicrosoftResellerLoggerUtil.debugServiceError(methodName,exception);
            MicrosoftResellerLoggerUtil.debug(methodName,exception.getResponseBodyAsString());
            throw new GeneralException(exception.getResponseBodyAsString());
        } catch (Exception exception) {
            MicrosoftResellerLoggerUtil.debugServiceError(methodName,exception);
            throw new GeneralException(exception.getMessage());
        }
    }

    @Override
    public List<OfferResource> getEligibleOffersByCountryCode(String countryCode) {
        String methodName = "getEligibleOffersByCountryCode";
        try {
            URI uri = createUriForGetEligibleOffersByCountryCode(countryCode);

            MicrosoftResellerLoggerUtil.debugServiceCall(methodName, uri.toString());

            ResponseEntity<CollectionResource<OfferResource>> responseEntity = microsoftResellerRestTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<CollectionResource<OfferResource>>() {});

            MicrosoftResellerLoggerUtil.debugServiceStatus(methodName, uri.toString(), responseEntity.getStatusCode().value());

            return Lists.newArrayList(responseEntity.getBody().getItems());
        } catch (HttpClientErrorException exception) {
            MicrosoftResellerLoggerUtil.debugServiceError(methodName,exception);
            MicrosoftResellerLoggerUtil.debug(methodName,exception.getResponseBodyAsString());
            throw new GeneralException(exception.getResponseBodyAsString());
        } catch (Exception exception) {
            MicrosoftResellerLoggerUtil.debugServiceError(methodName,exception);
            throw new GeneralException(exception.getMessage());
        }
    }

    private URI createUriForGetEligibleOffersByCountryCode(String countryCode) throws URISyntaxException {
        Map<String, String> placeHolder = new HashMap<>();
        placeHolder.put("country_id", countryCode);
        String uri = MessagePlaceHolderConverter.replaceVariablesInMessageWithValues(this.offerAvailibilityCheckUri, placeHolder);
        return new URI(new StringBuilder().append(this.baseUri).append(uri).toString());
    }

    @Override
    public List<OrderResource> getCustomerOrders(String customerId) {
        String methodName = "customerOrder";
        try {
            URI uri = createUriForCustomerOrders(customerId);

            MicrosoftResellerLoggerUtil.debugServiceCall(methodName, uri.toString());

            ResponseEntity<CollectionResource<OrderResource>> responseEntity = microsoftResellerRestTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<CollectionResource<OrderResource>>() {});

            MicrosoftResellerLoggerUtil.debugServiceStatus(methodName, uri.toString(), responseEntity.getStatusCode().value());

            return Lists.newArrayList(responseEntity.getBody().getItems());
        } catch (HttpClientErrorException exception) {
            MicrosoftResellerLoggerUtil.debugServiceError(methodName,exception);
            MicrosoftResellerLoggerUtil.debug(methodName,exception.getResponseBodyAsString());
            throw new GeneralException(exception.getResponseBodyAsString());
        } catch (Exception exception) {
            MicrosoftResellerLoggerUtil.debugServiceError(methodName,exception);
            throw new GeneralException(exception.getMessage());
        }
    }

    @Override
    public OrderResource createCustomerOrder(String customerId, OrderResource orderResource) {
        String methodName = "createCustomerOrder";
        try {
            URI uri = createUriForCustomerOrders(customerId);
            MicrosoftResellerLoggerUtil.debugServiceCall(methodName, uri.toString());

            ResponseEntity<OrderResource> responseEntity = microsoftResellerRestTemplate.postForEntity(uri, orderResource, OrderResource.class);

            MicrosoftResellerLoggerUtil.debugServiceStatus(methodName, uri.toString(), responseEntity.getStatusCode().value());

            return responseEntity.getBody();
        } catch (HttpClientErrorException exception) {
            MicrosoftResellerLoggerUtil.debugServiceError(methodName,exception);
            MicrosoftResellerLoggerUtil.debug(methodName,exception.getResponseBodyAsString());
            throw new GeneralException(exception.getResponseBodyAsString());
        } catch (Exception exception) {
            MicrosoftResellerLoggerUtil.debugServiceError(methodName,exception);
            throw new GeneralException(exception.getMessage());
        }
    }

    private URI createUriForCustomerOrders(String customerId) throws URISyntaxException {
        Map<String, String> placeHolder = new HashMap<>();
        placeHolder.put("customer_id", customerId);
        String uri = MessagePlaceHolderConverter.replaceVariablesInMessageWithValues(this.customerOrder, placeHolder);
        return new URI(new StringBuilder().append(this.baseUri).append(uri).toString());
    }

    @Override
    public List<SubscriptionResource> getCustomerSubscriptions(String customerId) {
        String methodName = "customerOrder";
        try {
            URI uri = createUriForCustomerSubscription(customerId);

            MicrosoftResellerLoggerUtil.debugServiceCall(methodName, uri.toString());

            ResponseEntity<CollectionResource<SubscriptionResource>> responseEntity = microsoftResellerRestTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<CollectionResource<SubscriptionResource>>() {});

            MicrosoftResellerLoggerUtil.debugServiceStatus(methodName, uri.toString(), responseEntity.getStatusCode().value());

            return Lists.newArrayList(responseEntity.getBody().getItems());
        } catch (HttpClientErrorException exception) {
            MicrosoftResellerLoggerUtil.debugServiceError(methodName,exception);
            MicrosoftResellerLoggerUtil.debug(methodName,exception.getResponseBodyAsString());
            throw new GeneralException(exception.getResponseBodyAsString());
        } catch (Exception exception) {
            MicrosoftResellerLoggerUtil.debugServiceError(methodName,exception);
            throw new GeneralException(exception.getMessage());
        }
    }

    private URI createUriForCustomerSubscription(String customerId) throws URISyntaxException {
        Map<String, String> placeHolder = Maps.newHashMap();
        placeHolder.put("customer_id", customerId);
        String uri = MessagePlaceHolderConverter.replaceVariablesInMessageWithValues(this.customerSubscription, placeHolder);
        return new URI(new StringBuilder().append(this.baseUri).append(uri).toString());
    }

    @Override
    public SubscriptionResource updateSubscription(String customerId, String subscriptionId, SubscriptionResource subscriptionResource) {
        String methodName = "updateSubscription";
        try {
            URI uri = createUriForCustomerOrders(customerId);
            MicrosoftResellerLoggerUtil.debugServiceCall(methodName, uri.toString());

            ResponseEntity<SubscriptionResource> responseEntity = microsoftResellerRestTemplate.exchange(
                    uri,
                    HttpMethod.PATCH,
                    new HttpEntity<>(subscriptionResource),
                    SubscriptionResource.class);

            MicrosoftResellerLoggerUtil.debugServiceStatus(methodName, uri.toString(), responseEntity.getStatusCode().value());

            return responseEntity.getBody();
        } catch (HttpClientErrorException exception) {
            MicrosoftResellerLoggerUtil.debugServiceError(methodName,exception);
            MicrosoftResellerLoggerUtil.debug(methodName,exception.getResponseBodyAsString());
            throw new GeneralException(exception.getResponseBodyAsString());
        } catch (Exception exception) {
            MicrosoftResellerLoggerUtil.debugServiceError(methodName,exception);
            throw new GeneralException(exception.getMessage());
        }
    }

    private URI createUriForUpdateCustomerSubscription(String customerId, String subscriptionId) throws URISyntaxException {
        Map<String, String> placeHolder = Maps.newHashMap();
        placeHolder.put("customer_id", customerId);
        String uri = MessagePlaceHolderConverter.replaceVariablesInMessageWithValues(this.customerSubscription, placeHolder);
        return new URI(new StringBuilder().append(this.baseUri).append(uri).append("/").append(subscriptionId).toString());
    }


}
