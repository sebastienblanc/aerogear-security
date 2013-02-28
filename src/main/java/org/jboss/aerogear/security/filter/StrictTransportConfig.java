package org.jboss.aerogear.security.filter;

import javax.servlet.FilterConfig;

public class StrictTransportConfig {

    private Long maxAge;
    private Boolean includeSubDomains;
    private String location;

    public StrictTransportConfig(FilterConfig config) {
        this.maxAge = Long.valueOf(config.getInitParameter("max-age"));
        this.includeSubDomains = Boolean.valueOf(config.getInitParameter("include-subdomains"));
        this.location = config.getInitParameter("location");
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
