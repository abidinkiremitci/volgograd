package io.oblomov.application.api.endpoint;

import io.oblomov.application.service.MicrosoftResellerService;
import io.oblomov.application.service.resource.SearchCustomerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    @Qualifier("microsoftResellerService")
    private MicrosoftResellerService microsoftResellerService;

    @RequestMapping(value = "/{domain}", method = RequestMethod.GET)
    public HttpEntity<List<SearchCustomerResource>> findById(@PathVariable(value = "domain") final String domain) {
        return new ResponseEntity<>(microsoftResellerService.searchCustomerByDomain(domain), HttpStatus.OK);
    }
}
