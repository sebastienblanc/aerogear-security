package org.jboss.aerogear.security.auth;

import org.jboss.aerogear.security.model.AeroGearUser;
import org.picketbox.core.authentication.credential.OTPCredential;
import org.picketlink.credential.LoginCredentials;

import javax.inject.Inject;

public class OtpCredentialProvider {

    @Inject
    private LoginCredentials credential;

    private OTPCredential otpCredential;

    public void credential(AeroGearUser user) {
        otpCredential = new OTPCredential(user.getId(), user.getPassword(), user.getOtp());
    }

    public Object getValue() {
        return otpCredential;
    }
}
