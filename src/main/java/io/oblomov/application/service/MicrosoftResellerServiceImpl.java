package io.oblomov.application.service;

import com.google.common.collect.Lists;
import io.oblomov.application.service.resource.Filter;
import io.oblomov.application.service.resource.SearchCustomerResource;
import io.oblomov.infra.exception.GeneralException;
import io.oblomov.infra.util.MessagePlaceHolderConverter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("microsoftResellerService")
public class MicrosoftResellerServiceImpl implements MicrosoftResellerService {

    private static final Logger log = LoggerFactory.getLogger(MicrosoftResellerServiceImpl.class);

    @Autowired
    private OAuth2RestTemplate microsoftResellerRestTemplate;

    @Setter
    @Value("${config.client.baseUri}")
    String baseUri;

    final static String searchCustomerUri = "/v1/customers?size=${size}&filter=${filter}"; // size=int, filter=UrlEncode({"Field":"Domain","Value":"umuttest","Operator":"starts_with"})

    @Override
    public List<SearchCustomerResource> searchCustomerByDomain(String domain) {
        try {
            String filterForSearchCustomer = createFilterForSearchCustomer(domain);
            URI uri = createUriForSearchCustomer(20, filterForSearchCustomer);

            debugServiceCall("searchCustomerByDomain",uri.toString());

            ResponseEntity<SearchCustomerResource> responseEntity = microsoftResellerRestTemplate.getForEntity(uri, SearchCustomerResource.class);

            debugServiceStatus("searchCustomerByDomain",uri.toString(),responseEntity.getStatusCode().value());

            return Lists.newArrayList(responseEntity.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException(e.getMessage());
        }
    }

    private URI createUriForSearchCustomer(int size, String filter) throws URISyntaxException {
        Map<String, String> placeHolder = new HashMap<>();
        placeHolder.put("size", String.valueOf(size));
        placeHolder.put("filter", filter);
        String uri = MessagePlaceHolderConverter.replaceVariablesInMessageWithValues(this.searchCustomerUri, placeHolder);
        return new URI(new StringBuilder().append(baseUri).append(uri).toString());
    }

    private String createFilterForSearchCustomer(String domainValue) throws IOException {
        Filter filter = Filter.builder()
                .field("Domain")
                .value(domainValue)
                .operator("starts_with")
                .build();
        return URLEncoder.encode(filter.toJson(),"UTF-8");
    }

    private void debugServiceCall(String methodName, String url) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("%s will call microsoft with url: %s", methodName, url));
        }
    }

    private void debugServiceStatus(String methodName, String url, int statusCode) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("%s called microsoft with url: %s, status code: %d", methodName, url, statusCode));
        }
    }
}
