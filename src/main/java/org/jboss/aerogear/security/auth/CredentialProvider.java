package org.jboss.aerogear.security.auth;

import org.jboss.aerogear.security.model.AeroGearUser;
import org.picketlink.credential.Credential;

public interface CredentialProvider extends Credential {

    void credential(AeroGearUser aeroGearUser);
}
