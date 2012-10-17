package org.jboss.aerogear.security.auth;

import org.jboss.aerogear.security.model.AeroGearUser;
import org.picketbox.core.authentication.credential.OTPCredential;
import org.picketbox.core.authentication.credential.UsernamePasswordCredential;
import org.picketlink.credential.LoginCredentials;

import javax.inject.Inject;

public class CredentialBuilderImpl implements CredentialBuilder {

    private Object value;

    @Inject
    private LoginCredentials loginCredentials;

    public void simpleCredential(AeroGearUser user) {
        this.value = new UsernamePasswordCredential(user.getId(), user.getPassword());
        loginCredentials.setCredential(this);
    }

    public void otpCredential(AeroGearUser user) {
        this.value = new OTPCredential(user.getId(), user.getPassword(), user.getOtp());
        loginCredentials.setCredential(this);
    }

    @Override
    public Object getValue() {
        return value;
    }
}
