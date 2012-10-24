package org.jboss.aerogear.security.api.auth;

import org.jboss.aerogear.security.impl.model.AeroGearUser;
import org.picketlink.credential.Credential;

public interface CredentialBuilder extends Credential {

    void simpleCredential(AeroGearUser user);

    void otpCredential(AeroGearUser user);
}
