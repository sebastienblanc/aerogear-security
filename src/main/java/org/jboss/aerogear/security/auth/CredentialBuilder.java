package org.jboss.aerogear.security.auth;

import org.jboss.aerogear.security.model.AeroGearUser;
import org.picketlink.credential.Credential;

public interface CredentialBuilder extends Credential {

    void simpleCredential(AeroGearUser user);

    void otpCredential(AeroGearUser user);
}
