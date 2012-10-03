package org.jboss.aerogear.security.dsl;

import org.jboss.picketlink.cdi.credential.Credential;

public interface AuthenticationManager extends Credential {

    public boolean login(String username, String password);
    public void logout();
}
