package org.jboss.aerogear.security.filter;

import javax.servlet.FilterConfig;

public class SecureHeadersConfig {

    private final Long maxAge;
    private final Boolean includeSubDomains;
    private final String location;
    private String frameOptions;

    public SecureHeadersConfig(FilterConfig config) {
        this.maxAge = Long.valueOf(config.getInitParameter("max-age"));
        this.includeSubDomains = Boolean.valueOf(config.getInitParameter("include-subdomains"));
        this.location = config.getInitParameter("Location");
        this.frameOptions = config.getInitParameter("x-frame-options");
    }

    public String getMaxAge() {
        String header = "max-age=" + maxAge;
        if (includeSubDomains) {
            header += "; includeSubdomains";
        }
        return header;
    }

    public String getLocation() {
        return location;
    }

    public String getFrameOptions() {
        return frameOptions;
    }
}
