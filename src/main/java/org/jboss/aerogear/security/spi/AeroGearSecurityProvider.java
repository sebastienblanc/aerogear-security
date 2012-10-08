package org.jboss.aerogear.security.spi;

import org.jboss.aerogear.controller.router.Route;
import org.jboss.aerogear.controller.spi.SecurityProvider;
import org.jboss.aerogear.security.exception.AeroGearSecurityException;
import org.jboss.picketlink.cdi.Identity;
import org.picketbox.cdi.PicketBoxUser;

import javax.inject.Inject;
import javax.servlet.ServletException;
import java.util.Set;

import static org.jboss.aerogear.security.exception.ExceptionMessage.AUTHENTICATION_FAILED;

public class AeroGearSecurityProvider implements SecurityProvider {

    @Inject
    private Identity identity;

    @Override
    public boolean isRouteAllowed(Route route) throws ServletException {

        if (identity.isLoggedIn()) {

            PicketBoxUser user = (PicketBoxUser) identity.getUser();
            Set<String> roles = route.getRoles();
            return user.getSubject().getRoleNames().containsAll(roles);
        }
        throw new AeroGearSecurityException(AUTHENTICATION_FAILED.toString(), AUTHENTICATION_FAILED.getStatus());
    }
}
