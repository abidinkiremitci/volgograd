package io.oblomov;

import io.oblomov.model.ResellerId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;

@RestController
class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final static String getResellerIdUri = "https://api.cp.microsoft.com/customers/get-by-identity?provider=AAD&type=tenant&tid=";
    @Value("${config.oauth2.microsoftId}")
    private String microsoftId;
    @Autowired
    private OAuth2RestOperations azureRestTemplate;

    @RequestMapping("/")
    public String home() {
        return "Hello world to Spring Boot!";
    }

    @RequestMapping("/get-access-token")
    public String getAccessToken() {
        String result = null;
        try {
            OAuth2AccessToken azureToken = azureRestTemplate.getAccessToken();
            result = azureToken.getValue();
        } catch (RestClientException exception) {
            log.debug(exception.getMessage());
            result = exception.getMessage();
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            result = exception.getMessage();
        }
        return result;
    }

    @RequestMapping("/get-reseller-id")
    public String getResellerId() {
        String result = null;
        try {
            ResellerId resellerId = azureRestTemplate.getForObject(getResellerIdUri + microsoftId, ResellerId.class, new HashMap<String, String>());
            result = resellerId.getId();
        } catch (RestClientException exception) {
            log.debug(exception.getMessage());
            result = exception.getMessage();
        } catch (Exception exception) {
            log.debug(exception.getMessage());
            result = exception.getMessage();
        }
        return result;
    }
}
