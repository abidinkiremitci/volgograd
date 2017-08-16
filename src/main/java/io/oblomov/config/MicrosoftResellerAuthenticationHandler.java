package io.oblomov.config;

import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.auth.ClientAuthenticationHandler;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

/**
 * Created by AbidinK on 31/10/2016.
 */
public class MicrosoftResellerAuthenticationHandler implements ClientAuthenticationHandler {

    public void authenticateTokenRequest(OAuth2ProtectedResourceDetails resource, MultiValueMap<String, String> form,
                                         HttpHeaders headers) {
        if (resource.isAuthenticationRequired()) {
            AuthenticationScheme scheme = AuthenticationScheme.header;
            if (resource.getClientAuthenticationScheme() != null) {
                scheme = resource.getClientAuthenticationScheme();
            }

            try {
                String clientSecret = resource.getClientSecret();
                clientSecret = clientSecret == null ? "" : clientSecret;
                switch (scheme) {
                    case header:
                        form.remove("client_secret");
                        form.remove("client_id");
                        break;
                    case form:
                    case query:
                        form.set("client_id", resource.getClientId());
                        form.set("client_secret", clientSecret);
                        if (resource instanceof MicrosoftResellerResourceDetails && !StringUtils.isEmpty(((MicrosoftResellerResourceDetails) resource).getResourceUri())) {
                            form.set("resource", ((MicrosoftResellerResourceDetails) resource).getResourceUri());
                        }
                        break;
                    default:
                        throw new IllegalStateException("Reseller authentication handler doesn't know how to handle scheme: " + scheme);
                }
            } catch (IllegalStateException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
