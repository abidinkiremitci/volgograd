package io.oblomov.infra.exception.handler;

import cz.jirutka.spring.exhandler.handlers.ErrorMessageRestExceptionHandler;
import cz.jirutka.spring.exhandler.messages.ErrorMessage;
import cz.jirutka.spring.exhandler.messages.ValidationErrorMessage;
import io.oblomov.infra.exception.GeneralException;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public class GeneralExceptionHandler<E extends GeneralException> extends ErrorMessageRestExceptionHandler<E> {

    public GeneralExceptionHandler(Class<E> exceptionClass, HttpStatus status) {
        super(exceptionClass, status);
    }

    protected GeneralExceptionHandler(HttpStatus status) {
        super(status);
    }

    @Override
    public ValidationErrorMessage createBody(E ex, HttpServletRequest req) {
        ErrorMessage errorMessage = createErrorMessage(ex, req);
        ValidationErrorMessage msg = new ValidationErrorMessage(errorMessage);
        for (String key : ex.getErrors().keySet()) {
            String value = ex.getErrors().get(key);
            msg.addError(key,null,value);
        }
        return msg;
    }

    private ErrorMessage createErrorMessage(E ex, HttpServletRequest req) {
        ErrorMessage m = new ErrorMessage();
        m.setType(URI.create(resolveMessage(TYPE_KEY, ex, req)));
        m.setTitle(resolveMessage(TITLE_KEY, ex, req));
        m.setStatus(getStatus());
        m.setDetail(ex.getMessage());
        m.setInstance(URI.create(resolveMessage(INSTANCE_KEY, ex, req)));
        return m;
    }
}
