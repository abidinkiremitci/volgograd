package io.oblomov.application.api.endpoint;

import io.oblomov.application.service.resource.CustomerResource;
import io.oblomov.application.service.resource.OrderResource;
import io.oblomov.application.service.resource.SubscriptionResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomerController extends BaseController {

    @RequestMapping(value = "/{domain}", method = RequestMethod.GET)
    public HttpEntity<List<CustomerResource>> findByDomain(@PathVariable(value = "domain") final String domain) {
        return new ResponseEntity<>(microsoftResellerService.searchCustomerByDomain(domain), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<CustomerResource> createCustomer(@RequestBody final CustomerResource customerResource) {
        return new ResponseEntity<>(microsoftResellerService.createCustomer(customerResource), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{customer_id}/orders", method = RequestMethod.GET)
    public HttpEntity<List<OrderResource>> getCustomerOrders(@PathVariable(value = "customer_id") final String customerId) {
        return new ResponseEntity<>(microsoftResellerService.getCustomerOrders(customerId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{customer_id}/orders", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<OrderResource> createCustomer(@PathVariable(value = "customer_id") final String customerId, @RequestBody final OrderResource orderResource) {
        return new ResponseEntity<>(microsoftResellerService.createCustomerOrder(customerId,orderResource), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{customer_id}/subscriptions/{subscription_id}", method = RequestMethod.PATCH, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<SubscriptionResource> createCustomer(
            @PathVariable(value = "customer_id") final String customerId,
            @PathVariable(value = "subscription_id") final String subscriptionId,
            @RequestBody final SubscriptionResource subscriptionResource) {
        return new ResponseEntity<>(microsoftResellerService.updateSubscription(customerId,subscriptionId,subscriptionResource), HttpStatus.CREATED);
    }


}
