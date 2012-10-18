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

        String serialNumber = idmuser.getAttribute("serial");
        if (serialNumber == null) {
            //Generate serial number
            serialNumber = UUID.randomUUID().toString();
            serialNumber = serialNumber.replace('-', 'c');

            //Just pick the first 10 characters
            serialNumber = serialNumber.substring(0, 10);

            serialNumber = Hex.toHexString(serialNumber.getBytes());
            idmuser.setAttribute("serial", serialNumber);
        }
        return serialNumber;
    }
}