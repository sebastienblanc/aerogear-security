package org.jboss.aerogear.security.auth;

import org.jboss.aerogear.security.annotation.Simple;
import org.jboss.aerogear.security.model.AeroGearUser;
import org.picketbox.core.authentication.credential.OTPCredential;
import org.picketbox.core.authentication.credential.UsernamePasswordCredential;

@Simple
public class SimpleCredentialBuilder implements CredentialBuilder {

    private Object credential;

    public void build(AeroGearUser user) {
        this.credential = new UsernamePasswordCredential(user.getId(), user.getPassword());
    }

    @Override
    public Object getValue() {
        return credential;
    }
}
