package io.oblomov.config;

import org.springframework.http.client.ClientHttpRequest;
import org.springframework.security.oauth2.client.DefaultOAuth2RequestAuthenticator;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import java.util.UUID;

/**
 * Created by AbidinK on 17/11/2016.
 */
public class MicrosoftResellerOAuth2RequestAuthenticator extends DefaultOAuth2RequestAuthenticator {

    @Override
    public void authenticate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext clientContext,
                             ClientHttpRequest request) {
        request.getHeaders().add("api-version", ((MicrosoftResellerResourceDetails)resource).getApiVersion());
        request.getHeaders().add("x-ms-tracking-id", UUID.randomUUID().toString());
        request.getHeaders().add("x-ms-correlation-id", UUID.randomUUID().toString());
        super.authenticate(resource, clientContext, request);
    }
}
