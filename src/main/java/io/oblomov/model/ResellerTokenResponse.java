package io.oblomov.model;


import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "access_token",
        "token_type",
        "expires_in"
})
public class ResellerTokenResponse {

    @Getter
    @Setter
    @JsonProperty("access_token")
    private String accessToken;
    @Getter
    @Setter
    @JsonProperty("token_type")
    private String tokenType;
    @Getter
    @Setter
    @JsonProperty("expires_in")
    private Integer expiresIn;
    @Getter
    @Setter
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}