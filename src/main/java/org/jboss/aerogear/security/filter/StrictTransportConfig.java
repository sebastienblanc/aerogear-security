package org.jboss.aerogear.security.filter;

import javax.servlet.FilterConfig;

public class StrictTransportConfig {

    private final Long maxAge;
    private final Boolean includeSubDomains;
    private final String location;

    public StrictTransportConfig(FilterConfig config) {
        this.maxAge = Long.valueOf(config.getInitParameter("max-age"));
        this.includeSubDomains = Boolean.valueOf(config.getInitParameter("include-subdomains"));
        this.location = config.getInitParameter("Location");
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
}
