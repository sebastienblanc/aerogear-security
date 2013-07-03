package org.jboss.aerogear.security.exception;

import com.wealdtech.DataError;
import com.wealdtech.ServerError;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

public class ExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(ExceptionHandler.class.getSimpleName());

    public static void handle(HttpServletResponse response, Exception exception) {

        Throwable e = exception.getCause();

        try {
            if (e instanceof DataError) {
                LOGGER.info("Error: " + e.getMessage());
                response.sendError(UNAUTHORIZED.getStatusCode());
            } else if (e instanceof ServerError) {
                LOGGER.info("Error: " + e.getMessage());
                response.sendError(INTERNAL_SERVER_ERROR.getStatusCode());
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
