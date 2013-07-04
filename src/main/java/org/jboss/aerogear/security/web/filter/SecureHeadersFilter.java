/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.security.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * HSTS servlet filter
 * For a detailed explanation please take a look at <a href="http://aerogear.org/docs/guides/aerogear-security/">http://aerogear.org/docs/guides/aerogear-security/</a>
 */
public class SecureHeadersFilter implements Filter {

    public static final String LOCATION = "Location";
    public static final String STRICT_TRANSPORT_SECURITY = "Strict-Transport-Security";
    public static final String X_FRAME_OPTIONS = "X-FRAME-OPTIONS";

    private SecureHeadersConfig config;

    public void init(FilterConfig filterConfig) throws ServletException {
        config = new SecureHeadersConfig(filterConfig);
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getScheme().equals("http")) {
            addLocation(response);
            addFrameOptions(response);
        } else if (request.getScheme().equals("https")) {
            addStrictTransportSecurity(response);
        }

        chain.doFilter(request, response);
    }

    private void addStrictTransportSecurity(HttpServletResponse response) {
        if (config.hasMaxAge()) {
            response.addHeader(STRICT_TRANSPORT_SECURITY, config.getMaxAge());
        }
    }

    private void addFrameOptions(HttpServletResponse response) {
        if (config.hasFrameOptions()) {
            response.addHeader(X_FRAME_OPTIONS, config.getFrameOptions());
        }
    }

    private void addLocation(HttpServletResponse response) {
        if (config.hasLocation()) {
            response.addHeader(LOCATION, config.getLocation());
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        }
    }
}
