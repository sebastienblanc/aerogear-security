/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.aerogear.security.exception;

import javax.ejb.EJBException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static org.jboss.aerogear.security.exception.HttpStatus.AUTHENTICATION_FAILED;

/**
 * Maps security exceptions to HTTP responses
 */
@Provider
public class HttpExceptionMapper implements ExceptionMapper<Throwable> {

    /**
     * @param exception Authentication/Authorization exceptions
     * @return HTTP response code
     */
    @Override
    public Response toResponse(Throwable exception) {

        if (exception instanceof EJBException) {
            Exception causedByException = ((EJBException) exception).getCausedByException();

            if (causedByException instanceof AeroGearSecurityException) {
                return Response.status(AUTHENTICATION_FAILED.getCode())
                        .entity(AUTHENTICATION_FAILED.getMessage())
                        .build();
            }
        }

        return Response.status(Response.Status.BAD_REQUEST).build();

    }
}
