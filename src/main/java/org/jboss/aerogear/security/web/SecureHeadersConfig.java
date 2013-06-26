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

package org.jboss.aerogear.security.web;

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

    public boolean hasMaxAge() {
        return maxAge != null && maxAge >= 0;
    }

    public boolean hasLocation() {
        return isEmpty(location);
    }

    public boolean hasFrameOptions() {
        return isEmpty(frameOptions);
    }

    private boolean isEmpty(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
