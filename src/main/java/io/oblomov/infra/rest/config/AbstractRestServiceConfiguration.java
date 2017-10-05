package io.oblomov.infra.rest.config;

/**
 * @author Burak Baldirlioglu
 * @since 10/7/2016 3:44 PM
 */
public abstract class AbstractRestServiceConfiguration implements RestServiceConfiguration {

    private static int DEFAULT_READ_TIMEOUT = 30_000;
    private static int DEFAULT_CONNECT_TIMEOUT = 5_000;

    @Override
    public boolean isLoggingEnabled() {
        return true;
    }

    @Override
    public int getReadTimeout() {
        return DEFAULT_READ_TIMEOUT;
    }

    @Override
    public int getConnectTimeout() {
        return DEFAULT_CONNECT_TIMEOUT;
    }

    @Override
    public boolean isSSLDisabled() {
        return false;
    }
}
