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

package org.jboss.aerogear.security.web.authz;

import org.jboss.aerogear.security.exception.ExceptionHandler;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <i>HawkAuthenticatorFilter</> Servlet filter to handle Hawk HTTP requests
 */
public class HawkAuthenticatorFilter implements Filter {

    @Inject
    private HawkAuthenticator hawkAuthenticator;

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain chain) {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        try {

            hawkAuthenticator.authenticate(request, response);

            if (!response.isCommitted()) {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            ExceptionHandler.handle(response, e);
        }
    }
}
