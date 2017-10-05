package io.oblomov.infra.rest.config.custom.microsoftreseller;

import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.UserApprovalRequiredException;
import org.springframework.security.oauth2.client.resource.UserRedirectRequiredException;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.OAuth2AccessTokenSupport;
import org.springframework.security.oauth2.client.token.auth.ClientAuthenticationHandler;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Iterator;
import java.util.List;

public class MicrosoftResellerTokenProvider extends OAuth2AccessTokenSupport implements AccessTokenProvider {

    private OAuth2AccessToken clientToken;

    public MicrosoftResellerTokenProvider(ClientAuthenticationHandler clientAuthenticationHandler) {
        this.setAuthenticationHandler(clientAuthenticationHandler);
    }

    @Override
    public OAuth2AccessToken obtainAccessToken(OAuth2ProtectedResourceDetails details, AccessTokenRequest request) throws UserRedirectRequiredException, UserApprovalRequiredException, AccessDeniedException {

        MicrosoftResellerResourceDetails resource = (MicrosoftResellerResourceDetails)details;
        String userTokerUri = resource.getUserAuthorizationUri();
        String accessTokenUri= resource.getAccessTokenUri();
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        if (clientToken == null || clientToken.isExpired()) {
            configureParametersForTokenRequest(resource,form);
            resource.setClientAuthenticationScheme(AuthenticationScheme.query);
            resource.setAccessTokenUri(userTokerUri);
            clientToken = retrieveToken(request, resource, form, new HttpHeaders());
        }
        resource.setClientAuthenticationScheme(AuthenticationScheme.header);
        resource.setAccessTokenUri(accessTokenUri);
        configureParametersForTokenRequest(resource,form);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",String.format("%s %s", clientToken.getTokenType(),clientToken.getValue()));
        return retrieveToken(request, resource, form, headers);
    }

    private void configureParametersForTokenRequest(MicrosoftResellerResourceDetails resource, MultiValueMap<String, String> form) {

        form.clear();
        if(this.clientToken==null) {
            form.set("grant_type", "client_credentials");
        } else {
            form.set("grant_type", "jwt_token");
        }

        if (resource.isScoped()) {

            StringBuilder builder = new StringBuilder();
            List<String> scope = resource.getScope();

            if (scope != null) {
                Iterator<String> scopeIt = scope.iterator();
                while (scopeIt.hasNext()) {
                    builder.append(scopeIt.next());
                    if (scopeIt.hasNext()) {
                        builder.append(' ');
                    }
                }
            }

            form.set("scope", builder.toString());
        }
    }

    @Override
    public boolean supportsResource(OAuth2ProtectedResourceDetails resource) {
        return resource instanceof MicrosoftResellerResourceDetails
                && "client_credentials".equals(resource.getGrantType());
    }

    @Override
    public OAuth2AccessToken refreshAccessToken(OAuth2ProtectedResourceDetails resource, OAuth2RefreshToken refreshToken, AccessTokenRequest request) throws UserRedirectRequiredException {
        return null;
    }

    @Override
    public boolean supportsRefresh(OAuth2ProtectedResourceDetails resource) {
        return false;
    }
}
