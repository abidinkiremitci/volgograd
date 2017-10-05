package io.oblomov.infra.rest.config;

import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * @author Burak Baldirlioglu
 * @since 10/7/2016 2:34 PM
 */
public interface RestServiceConfiguration {

    OAuth2ProtectedResourceDetails getResource();

    boolean isLoggingEnabled();

    int getReadTimeout();

    int getConnectTimeout();

    boolean isSSLDisabled();
}
