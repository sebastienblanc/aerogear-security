package org.jboss.aerogear.security.auth;

import org.jboss.aerogear.security.model.AeroGearUser;
import org.picketlink.credential.Credential;

public interface AuthenticationScheme extends Credential {

    void configure(AeroGearUser aeroGearUser);
}
