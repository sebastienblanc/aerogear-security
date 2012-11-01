package org.jboss.aerogear.security.auth;

import org.jboss.aerogear.security.model.AeroGearUser;

public interface CredentialFactory {

    void setCredential(AeroGearUser user);

}
