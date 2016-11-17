package io.oblomov;

import org.springframework.http.client.ClientHttpRequest;
import org.springframework.security.oauth2.client.DefaultOAuth2RequestAuthenticator;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * Created by AbidinK on 17/11/2016.
 */
public class ResellerOAuth2RequestAuthenticator extends DefaultOAuth2RequestAuthenticator {
    @Override
    public void authenticate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext clientContext,
                             ClientHttpRequest request) {
        request.getHeaders().add("api-version", "2015-03-31");
        super.authenticate(resource, clientContext, request);
    }
}
