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
import java.io.IOException;

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
        } catch (ServletException e) {
            ExceptionHandler.handle(response, e);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
