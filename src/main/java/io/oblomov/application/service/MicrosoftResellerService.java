package io.oblomov.application.service;

import io.oblomov.application.service.resource.SearchCustomerResource;

import java.util.List;

public interface MicrosoftResellerService {

    List<SearchCustomerResource>  searchCustomerByDomain(String domain);

}
