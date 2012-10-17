package org.jboss.aerogear.security.auth;

import org.jboss.aerogear.security.model.AeroGearUser;

public interface AuthenticationManager {

    boolean login(AeroGearUser user);

    void logout();
}
