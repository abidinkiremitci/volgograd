package io.oblomov.infra.rest.config;

import io.oblomov.infra.rest.config.custom.microsoftreseller.MicrosoftResellerResourceDetails;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import java.util.UUID;

@Configuration
public class MicrosoftResellerRestServiceConfiguration extends AbstractRestServiceConfiguration {

    @Setter
    @Value("${config.oauth2.azureTokenUri}")
    private String accessTokenUri;

    @Setter
    @Value("${config.oauth2.clientID}")
    private String clientID;

    @Setter
    @Value("${config.oauth2.clientSecret}")
    private String clientSecret;

    @Setter
    @Value("${config.oauth2.resellerTokenUri}")
    private String resellerTokenUri;

    @Setter
    @Value("${config.oauth2.resourceUri}")
    private String resourceUri;

    @Setter
    @Value("${config.oauth2.apiVersion}")
    private String resellerMicrosoftApiVersion;

    @Setter
    private Integer resellerMicrosoftReadTimeout = 90_000;

    @Setter
    private Integer resellerMicrosoftConnectTimeout = 30_000;

    @Override
    public OAuth2ProtectedResourceDetails getResource() {
        MicrosoftResellerResourceDetails resource = new MicrosoftResellerResourceDetails();
        resource.setId(UUID.randomUUID().toString());
        resource.setClientId(clientID);
        resource.setClientSecret(clientSecret);
        resource.setAccessTokenUri(resellerTokenUri);
        resource.setUserAuthorizationUri(accessTokenUri);
        resource.setResourceUri(resourceUri);
        resource.setApiVersion(resellerMicrosoftApiVersion);
        return resource;
    }

    @Override
    public int getReadTimeout() {
        return resellerMicrosoftReadTimeout;
    }

    @Override
    public int getConnectTimeout() {
        return resellerMicrosoftConnectTimeout;
    }

    @Override
    public boolean isSSLDisabled() {
        return false;
    }
}
