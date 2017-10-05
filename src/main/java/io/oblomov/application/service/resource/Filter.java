package io.oblomov.application.service.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Data
public class Filter {

    @JsonProperty("Field")
    String field;

    @JsonProperty("Value")
    String value;

    @JsonProperty("Operator")
    String operator;

    public String toJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        objectMapper.writeValue(outputStream, this);
        return outputStream.toString("UTF-8");
    }

    @Builder
    public Filter(String field, String value, String operator) {
        this.field = field;
        this.value = value;
        this.operator = operator;
    }
}
