package io.oblomov.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by AbidinK on 18.10.2017.
 */
public class MicrosoftResellerLoggerUtil {

    private static final Logger log = LoggerFactory.getLogger(MicrosoftResellerServiceImpl.class);

    public static void debugServiceCall(String methodName, String url) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("%s will call microsoft with url: %s", methodName, url));
        }
    }

    public static void debugServiceStatus(String methodName, String url, int statusCode) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("%s called microsoft with url: %s, status code: %d", methodName, url, statusCode));
        }
    }

    public static void debugServiceError(String methodName, Exception exception) {
        if (log.isDebugEnabled()) {
            log.debug(String.format(methodName + " called microsoft with error: " + exception));
        }
        log.error(String.format(String.format("%s called microsoft with error: %s", methodName, exception.getMessage())));
    }

    public static void debug(String methodName, String text) {
        if (log.isDebugEnabled()) {
            log.debug(String.format(methodName + " points to:  " + text));
        }
    }

}
