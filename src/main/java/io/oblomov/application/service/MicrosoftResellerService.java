package io.oblomov.application.service;

import io.oblomov.application.service.resource.CustomerResource;
import io.oblomov.application.service.resource.OfferResource;
import io.oblomov.application.service.resource.OrderResource;
import io.oblomov.application.service.resource.SubscriptionResource;

import java.util.List;

public interface MicrosoftResellerService {

    List<CustomerResource>  searchCustomerByDomain(String domain);

    CustomerResource createCustomer(CustomerResource customerResource);

    List<OfferResource> getEligibleOffersByCountryCode(String countryCode);

    List<OrderResource> getCustomerOrders(String customerId);

    OrderResource createCustomerOrder(String customerId, OrderResource orderResource);

    List<SubscriptionResource> getCustomerSubscriptions(String customerId);

    SubscriptionResource updateSubscription(String customerId, String subscriptionId, SubscriptionResource subscriptionResource);
}
