package org.jboss.aerogear.security.authz;

public interface AuthorizationManager {

    boolean validate(String token);
}
