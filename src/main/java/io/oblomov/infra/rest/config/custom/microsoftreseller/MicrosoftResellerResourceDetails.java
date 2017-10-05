package io.oblomov.infra.rest.config.custom.microsoftreseller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.client.token.grant.redirect.AbstractRedirectResourceDetails;

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
