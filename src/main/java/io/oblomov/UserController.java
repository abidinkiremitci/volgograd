package io.oblomov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private OAuth2RestOperations azureRestTemplate;

    @Autowired
    private OAuth2RestOperations resellerRestTemplate;

    @RequestMapping("/")
    public String home() {
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

}