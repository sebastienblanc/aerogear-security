package org.jboss.aerogear.security.dsl;

import org.jboss.aerogear.security.model.AeroGearUser;
import org.jboss.picketlink.cdi.credential.Credential;

public interface AuthenticationManager extends Credential {

    public boolean login(AeroGearUser user);
    public void logout();
}
