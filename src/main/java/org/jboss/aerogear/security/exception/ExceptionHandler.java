package org.jboss.aerogear.security.exception;

import com.wealdtech.DataError;
import com.wealdtech.ServerError;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

public class ExceptionHandler {

    public static void handle(HttpServletResponse response, Exception exception) {

        Throwable e = exception.getCause();


        if (e instanceof DataError) {
            responseWriter(response, UNAUTHORIZED.getStatusCode(), e.getMessage());
        } else if (e instanceof ServerError) {
            responseWriter(response, INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage());
        }

    }

    private static void responseWriter(HttpServletResponse response, int status, String message) {

        try {

            response.setHeader("WWW-Authenticate", "Hawk");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(message);
            response.setStatus(status);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
