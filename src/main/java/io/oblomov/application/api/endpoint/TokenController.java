package io.oblomov.application.api.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

@RestController
@RequestMapping(path = "/api/v1/token")
class TokenController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(TokenController.class);

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
}
