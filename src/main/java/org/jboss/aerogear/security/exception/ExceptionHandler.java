/**
 * JBoss, Home of Professional Open Source
 *  Copyright Red Hat, Inc., and individual contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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
