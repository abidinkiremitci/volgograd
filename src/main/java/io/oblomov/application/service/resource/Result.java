package io.oblomov.application.service.resource;

@lombok.Data
public class Result {
    private int code;
    private String message;

    public static Result handleResult(int code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
