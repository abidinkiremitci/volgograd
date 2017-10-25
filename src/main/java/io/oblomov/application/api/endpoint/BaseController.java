package io.oblomov.application.api.endpoint;

import io.oblomov.application.service.MicrosoftResellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;

/**
 * Created by AbidinK on 18.10.2017.
 */
public abstract class BaseController {

    @Autowired
    protected MicrosoftResellerService microsoftResellerService;

    @Autowired
    protected OAuth2RestOperations azureRestTemplate;
}
