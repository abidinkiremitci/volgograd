package io.oblomov.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.client.token.grant.redirect.AbstractRedirectResourceDetails;

/**
 * Created by AbidinK on 31/10/2016.
 */
public class ResellerResourceDetails extends AbstractRedirectResourceDetails {

    @Getter
    @Setter
    private String resourceUri;

    public ResellerResourceDetails() {
        setGrantType("client_credentials");
    }
}
