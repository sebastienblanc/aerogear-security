package org.jboss.aerogear.security.cdi;

import org.jboss.aerogear.security.annotations.LoggedIn;
import org.jboss.aerogear.security.annotations.SessionToken;
import org.picketbox.cdi.PicketBoxIdentity;
import org.picketlink.idm.model.User;

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

    @Produces
    @LoggedIn
    public User getUser() {
        return identity.getUserContext().getUser();
    }
}
