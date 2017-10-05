package io.oblomov.application.service.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "length"
})
@Data
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType
public class SecureStringResource {

    @JsonProperty("length")
    int length;
}
