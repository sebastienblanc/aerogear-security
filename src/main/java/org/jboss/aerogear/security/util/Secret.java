package org.jboss.aerogear.security.util;

import org.picketbox.cdi.PicketBoxIdentity;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.model.User;

import javax.inject.Inject;
import java.util.UUID;

public class Secret {

    @Inject
    private PicketBoxIdentity identity;

    @Inject
    private IdentityManager identityManager;

    public String generate() {

        User user = identity.getUserContext().getUser();
        User idmuser = identityManager.getUser(user.getKey());

        String secret = idmuser.getAttribute("serial");
        if (secret == null) {
            //Generate serial number
            secret = UUID.randomUUID().toString();
            secret = secret.replace('-', 'c');

            //Just pick the first 10 characters
            secret = secret.substring(0, 10);

            secret = Hex.toHexString(secret.getBytes());
            idmuser.setAttribute("serial", secret);
        }
        return secret;
    }
}