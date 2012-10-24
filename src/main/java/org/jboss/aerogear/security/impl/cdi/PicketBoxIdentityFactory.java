package org.jboss.aerogear.security.impl.cdi;

import org.jboss.aerogear.security.api.annotations.SessionToken;
import org.picketbox.cdi.PicketBoxIdentity;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.io.Serializable;

@SessionScoped
public class PicketBoxIdentityFactory implements Serializable {

    @Inject
    private PicketBoxIdentity identity;

    @Produces
    @SessionToken
    public String getToken() {
        String token = null;
        if (identity.isLoggedIn())
            token = identity.getUserContext().getSession().getId().getId().toString();

        return token;
    }
}
