package org.jboss.aerogear.security.api.auth;

import org.jboss.aerogear.security.impl.model.AeroGearUser;

public interface AuthenticationManager {

    boolean login(AeroGearUser aeroGearUser);

    void logout();
}
