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
        if (config.getMaxAge() != null && !config.getMaxAge().trim().isEmpty()) {
            response.addHeader(STRICT_TRANSPORT_SECURITY, config.getMaxAge());
        }
    }

    private void addFrameOptions(HttpServletResponse response) {
        if (config.getFrameOptions() != null && !config.getFrameOptions().trim().isEmpty()) {
            response.addHeader(X_FRAME_OPTIONS, config.getFrameOptions());
        }
    }

    private void addLocation(HttpServletResponse response) {
        if (config.getLocation() != null && !config.getLocation().trim().isEmpty()) {
            response.addHeader(LOCATION, config.getLocation());
            response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        }
    }
}
