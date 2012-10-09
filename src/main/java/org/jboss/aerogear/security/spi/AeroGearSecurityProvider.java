package org.jboss.aerogear.security.spi;

import org.jboss.aerogear.controller.router.Route;
import org.jboss.aerogear.controller.spi.SecurityProvider;
import org.jboss.aerogear.security.exception.ExceptionMessage;
import org.jboss.picketlink.cdi.Identity;
import org.picketbox.cdi.PicketBoxUser;

import javax.inject.Inject;
import javax.servlet.ServletException;
import java.util.Set;

public class AeroGearSecurityProvider implements SecurityProvider {

    @Inject
    private Identity identity;

    @Override
    public void isRouteAllowed(Route route) throws ServletException {

        PicketBoxUser user = (PicketBoxUser) identity.getUser();
        Set<String> roles = route.getRoles();
        boolean hasRoles = user.getSubject().getRoleNames().containsAll(roles);

        if (!identity.isLoggedIn() || !hasRoles) {
            ExceptionMessage.AUTHENTICATION_FAILED.throwException();
        }
    }
}
