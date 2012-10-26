package org.jboss.aerogear.security.idm;

public interface AuthenticationKeyProvider {

    String getSecret();

    String getB32();

    Object getToken();
}
