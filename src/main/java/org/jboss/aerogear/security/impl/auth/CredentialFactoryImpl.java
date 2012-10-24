package org.jboss.aerogear.security.impl.auth;

import org.jboss.aerogear.security.api.auth.CredentialFactory;
import org.jboss.aerogear.security.impl.model.AeroGearUser;
import org.picketbox.core.authentication.credential.OTPCredential;
import org.picketbox.core.authentication.credential.UsernamePasswordCredential;
import org.picketlink.credential.LoginCredentials;

import javax.inject.Inject;

public class CredentialFactoryImpl implements CredentialFactory {

    private Object credential;

    @Inject
    private LoginCredentials loginCredentials;

    public void setCredential(AeroGearUser user) {
        this.credential = new OTPCredential(user.getId(), user.getPassword(), user.getOtp());
        loginCredentials.setCredential(this);
    }

    @Override
    public Object getValue() {
        return credential;
    }
}
