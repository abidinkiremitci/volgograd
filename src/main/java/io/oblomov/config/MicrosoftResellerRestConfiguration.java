package io.oblomov.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

import java.util.UUID;

/**
 * Created by AbidinK on 23/10/16.
 */
@Configuration
public class MicrosoftResellerRestConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MicrosoftResellerRestConfiguration.class);

    @Value("${config.oauth2.azureTokenUri}")
    private String accessTokenUri;

    @Value("${config.oauth2.clientID}")
    private String clientID;

    @Value("${config.oauth2.clientSecret}")
    private String clientSecret;

    @Value("${config.oauth2.resellerTokenUri}")
    private String resellerTokenUri;

    @Value("${config.oauth2.resourceUri}")
    private String resourceUri;

    @Value("${config.oauth2.apiVersion}")
    private String apiVersion;

    @Bean
    public OAuth2RestOperations azureRestTemplate() {
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(getAzureResource(), new DefaultOAuth2ClientContext());
        restTemplate.setAuthenticator(new MicrosoftResellerOAuth2RequestAuthenticator());
        restTemplate.setAccessTokenProvider(new MicrosoftResellerTokenProvider(new MicrosoftResellerAuthenticationHandler()));
        return restTemplate;
    }

    private OAuth2ProtectedResourceDetails getAzureResource() {
        MicrosoftResellerResourceDetails resource = new MicrosoftResellerResourceDetails();
        resource.setId(UUID.randomUUID().toString());
        resource.setClientId(clientID);
        resource.setClientSecret(clientSecret);
        resource.setAccessTokenUri(resellerTokenUri);
        resource.setUserAuthorizationUri(accessTokenUri);
        resource.setResourceUri(resourceUri);
        resource.setApiVersion(apiVersion);
        return resource;
    }
}
