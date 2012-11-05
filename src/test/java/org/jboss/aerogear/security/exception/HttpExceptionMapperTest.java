/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
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

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static org.junit.Assert.assertEquals;

public class HttpExceptionMapperTest {

    private AeroGearSecurityException securityException;
    private HttpExceptionMapper exceptionMapper;

    @Before
    public void setUp() throws Exception {
        securityException = new AeroGearSecurityException(HttpStatus.AUTHENTICATION_FAILED);
        exceptionMapper = new HttpExceptionMapper();
    }

    @Test
    public void testToResponseAeroGearSecurityException() throws Exception {
        Response response = exceptionMapper.toResponse(securityException);
        assertEquals(UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testToResponseAnyException() throws Exception {
        Exception exception = new Exception();
        Response response = exceptionMapper.toResponse(exception);
        assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}
