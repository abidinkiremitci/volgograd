package io.oblomov.config;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.security.oauth2.client.DefaultOAuth2RequestAuthenticator;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * Created by AbidinK on 17/11/2016.
 */
public class ResellerOAuth2RequestAuthenticator extends DefaultOAuth2RequestAuthenticator {

    @Setter
    @Value("${config.oauth2.microsoft-api-version}")
    private String resellerApiVersion;

    @Override
    public void authenticate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext clientContext,
                             ClientHttpRequest request) {
        request.getHeaders().add("api-version", resellerApiVersion);
        super.authenticate(resource, clientContext, request);
    }
}
