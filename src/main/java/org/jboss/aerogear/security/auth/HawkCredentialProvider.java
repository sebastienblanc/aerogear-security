package org.jboss.aerogear.security.auth;

import com.wealdtech.hawk.HawkCredentials;

/**
 * <i>HawkCredentialProvider</i> allows developers to implement their own provider.
 */
public interface HawkCredentialProvider {

    /**
     * @param keyID represents the key ID to retrieve an credential
     * @return HawkCredentials represent the information required for client/server authentication
     */
    public HawkCredentials findByKey(String keyID);
}
