package org.jboss.aerogear.security.api.auth;

public interface AuthenticationManager {

    boolean login();

    void logout();
}
