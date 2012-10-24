package org.jboss.aerogear.security.api.auth;

import org.jboss.aerogear.security.impl.model.AeroGearUser;
import org.picketlink.credential.Credential;

public interface CredentialFactory extends Credential {

    void setCredential(AeroGearUser user);

}
