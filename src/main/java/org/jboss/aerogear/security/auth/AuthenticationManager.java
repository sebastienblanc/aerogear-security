package org.jboss.aerogear.security.auth;

public interface AuthenticationManager {

    boolean login();

    void logout();
}
