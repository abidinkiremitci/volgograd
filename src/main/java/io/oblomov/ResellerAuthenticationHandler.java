package io.oblomov;

import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.auth.ClientAuthenticationHandler;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by AbidinK on 31/10/2016.
 */
public class ResellerAuthenticationHandler implements ClientAuthenticationHandler {

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
                        if (resource instanceof ResellerResourceDetails && !StringUtils.isEmpty(((ResellerResourceDetails)resource).getResourceUri())) {
                            form.set("resource", ((ResellerResourceDetails)resource).getResourceUri());
                        }
                        break;
                    default:
                        throw new IllegalStateException("Reseller authentication handler doesn't know how to handle scheme: " + scheme);
                }
            }
            catch (IllegalStateException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
