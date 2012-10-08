package org.jboss.aerogear.security.dsl;

import org.jboss.aerogear.security.model.AeroGearUser;
import org.jboss.picketlink.cdi.credential.Credential;

public interface AuthenticationManager extends Credential {

    boolean login(AeroGearUser user);

    void logout();
}
