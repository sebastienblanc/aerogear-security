package org.jboss.aerogear.security.auth;

import org.jboss.aerogear.security.annotation.Otp;
import org.jboss.aerogear.security.model.AeroGearUser;
import org.picketbox.core.authentication.credential.OTPCredential;

@Otp
public class OtpCredentialBuilder implements CredentialBuilder {

    private Object credential;

    public void build(AeroGearUser user) {
        this.credential = new OTPCredential(user.getId(), user.getPassword(), user.getOtp());
    }

    @Override
    public Object getValue() {
        return credential;
    }
}
