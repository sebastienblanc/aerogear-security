package org.jboss.aerogear.security.dsl;

import org.jboss.aerogear.security.auth.CredentialProvider;
import org.jboss.aerogear.security.model.AeroGearUser;
import org.picketlink.credential.Credential;

public interface AuthenticationManager {

    void configure(CredentialProvider provider);

    boolean login(AeroGearUser user);

    void logout();
}
