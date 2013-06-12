/*
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

package org.jboss.aerogear.security.filter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SecureHeadersFilterTest {

    public static final String STRICT_TRANSPORT_SECURITY = "Strict-Transport-Security";

    @InjectMocks
    private SecureHeadersFilter filter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private FilterConfig config;

    @Before
    public void setUp() throws ServletException {
        MockitoAnnotations.initMocks(this);
        when(config.getInitParameter("max-age")).thenReturn("2592000");
        when(config.getInitParameter("include-subdomains")).thenReturn("true");
        when(config.getInitParameter("Location")).thenReturn("https://john.doe");
        filter = new SecureHeadersFilter();
        filter.init(config);
    }

    @Test
    public void testNonHttpsRequest() throws Exception {
        when(request.getScheme()).thenReturn("http");
        filter.doFilter(request, response, filterChain);
        verify(response).addHeader("Location", "https://john.doe");
        verify(response).setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
    }

    @Test
    public void testHttpsRequest() throws Exception {
        when(request.getScheme()).thenReturn("https");
        filter.doFilter(request, response, filterChain);
        verify(response).addHeader(STRICT_TRANSPORT_SECURITY, "max-age=2592000; includeSubdomains");
    }
}
