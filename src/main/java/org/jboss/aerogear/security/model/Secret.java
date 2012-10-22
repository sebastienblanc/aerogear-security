package org.jboss.aerogear.security.model;

import org.jboss.aerogear.security.util.Hex;
import org.picketbox.cdi.PicketBoxIdentity;
import org.picketbox.core.util.Base32;
import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.model.User;

import javax.inject.Inject;
import java.util.UUID;

public class Secret {

    @Inject
    private PicketBoxIdentity identity;

    @Inject
    private IdentityManager identityManager;

    private String id;
    private String secret;
    private String b32;

    public Secret generate() {

        User user = identity.getUserContext().getUser();
        User idmuser = identityManager.getUser(user.getKey());

        secret = idmuser.getAttribute("serial");
        if (secret == null) {
            //Generate serial number
            secret = UUID.randomUUID().toString();
            secret = secret.replace('-', 'c');

            //Just pick the first 10 characters
            secret = secret.substring(0, 10);

            secret = Hex.toHexString(secret.getBytes());
            idmuser.setAttribute("serial", secret);
        }
        return this;
    }

    public String getId() {
        return identity.getUserContext().getUser().getKey();
    }

    public String getB32() {
        return Base32.encode(Hex.hexToAscii(secret).getBytes());
    }

    public String getSecret() {
        return secret;
    }

}