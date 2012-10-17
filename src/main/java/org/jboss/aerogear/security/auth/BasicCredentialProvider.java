package org.jboss.aerogear.security.auth;

import org.jboss.aerogear.security.model.AeroGearUser;
import org.picketbox.core.authentication.credential.UsernamePasswordCredential;
import org.picketlink.credential.LoginCredentials;

import javax.inject.Inject;

public class BasicCredentialProvider implements CredentialProvider {

    @Inject
    private LoginCredentials credential;

    private UsernamePasswordCredential usernamePasswordCredential;

    @Override
    public Object getValue() {
        return usernamePasswordCredential;
    }

    @Override
    public void credential(AeroGearUser user) {
        credential.setCredential(this);
        usernamePasswordCredential = new UsernamePasswordCredential(user.getId(), user.getPassword());
    }
}
