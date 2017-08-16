package io.oblomov.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.client.token.grant.redirect.AbstractRedirectResourceDetails;

/**
 * Created by AbidinK on 31/10/2016.
 */
public class MicrosoftResellerResourceDetails extends AbstractRedirectResourceDetails {

    @Getter
    @Setter
    private String resourceUri;

    @Getter
    @Setter
    private String apiVersion;

    public MicrosoftResellerResourceDetails() {
        setGrantType("jwt_token");
    }
}
