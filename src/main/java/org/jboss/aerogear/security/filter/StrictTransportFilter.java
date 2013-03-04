package org.jboss.aerogear.security.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StrictTransportFilter implements Filter {

    public static final String LOCATION = "Location";
    public static final String STRICT_TRANSPORT_SECURITY = "Strict-Transport-Security";
    private StrictTransportConfig config;

    public void init(FilterConfig filterConfig) throws ServletException {
        config = new StrictTransportConfig(filterConfig);
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (!isHttpsEnabled(request)) {
            response.addHeader(LOCATION, config.getLocation());
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);

        } else {
            response.addHeader(STRICT_TRANSPORT_SECURITY, config.getMaxAge());
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    private static boolean isHttpsEnabled(HttpServletRequest httpServletRequest) {

        final String HTTPS = "https";

        if (httpServletRequest.getScheme().equals(HTTPS)) {
            return true;
        }
        return false;
    }
}
