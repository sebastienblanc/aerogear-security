package org.jboss.aerogear.security.auth;

import org.jboss.aerogear.security.model.AeroGearUser;
import org.picketbox.core.authentication.credential.OTPCredential;
import org.picketlink.credential.LoginCredentials;

import javax.inject.Inject;

public class OtpAuthenticationScheme implements AuthenticationScheme {

    @Inject
    private LoginCredentials credential;

    private OTPCredential otpCredential;

    @Override
    public void configure(AeroGearUser user) {
        otpCredential = new OTPCredential(user.getId(), user.getPassword(), user.getOtp());
    }

    @Override
    public Object getValue() {
        return otpCredential;
    }
}
