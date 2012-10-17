package org.jboss.aerogear.security.auth;

import org.jboss.aerogear.security.model.AeroGearUser;
import org.picketbox.core.authentication.credential.OTPCredential;
import org.picketbox.core.authentication.credential.UsernamePasswordCredential;

public class CredentialBuilderImpl implements CredentialBuilder {

    private Object credential;

    public void simpleCredential(AeroGearUser user) {
        this.credential = new UsernamePasswordCredential(user.getId(), user.getPassword());
    }

    public void otpCredential(AeroGearUser user) {
        this.credential = new OTPCredential(user.getId(), user.getPassword(), user.getOtp());
    }

    @Override
    public Object getValue() {
        return credential;
    }
}
