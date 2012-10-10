package org.jboss.aerogear.security.spi;

import org.jboss.aerogear.controller.router.Route;
import org.jboss.aerogear.controller.spi.SecurityProvider;
import org.jboss.aerogear.security.exception.ExceptionMessage;
import org.jboss.picketlink.cdi.Identity;
import org.picketbox.cdi.PicketBoxUser;

import javax.inject.Inject;
import javax.servlet.ServletException;

public class AeroGearSecurityProvider implements SecurityProvider {

    @Inject
    private Identity identity;

    @Override
    public void isRouteAllowed(Route route) throws ServletException {

        PicketBoxUser user = (PicketBoxUser) identity.getUser();
        boolean hasRoles = hasRoles(user, route);

        if (!identity.isLoggedIn() || !hasRoles) {
            ExceptionMessage.AUTHENTICATION_FAILED.throwException();
        }
    }

    //TODO must to be refactored
    private boolean hasRoles(PicketBoxUser user, Route route) {
        boolean hasRoles = false;
        if (user != null && route != null) {
            hasRoles = user.getSubject().getRoleNames().containsAll(route.getRoles());
        }
        return hasRoles;
    }
}
