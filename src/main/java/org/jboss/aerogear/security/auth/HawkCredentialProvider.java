package org.jboss.aerogear.security.auth;

import com.wealdtech.hawk.HawkCredentials;

public interface HawkCredentialProvider {

    public HawkCredentials findByKey(String key);
}
