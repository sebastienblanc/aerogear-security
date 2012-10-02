package org.jboss.aerogear.security.spi;

import org.jboss.aerogear.controller.router.Route;
import org.jboss.aerogear.controller.spi.SecurityProvider;
import org.jboss.picketlink.cdi.Identity;
import org.picketbox.cdi.PicketBoxUser;

import javax.inject.Inject;
import javax.servlet.ServletException;

//TODO must be improved
public class AeroGearSecurityProvider implements SecurityProvider {

    @Inject
    private Identity identity;

    @Override
    public boolean isRouteAllowed(Route route) throws ServletException {

        System.out.println("Security provider");

        if (identity.isLoggedIn()) {
            PicketBoxUser user = (PicketBoxUser) identity.getUser();
            boolean isAllowed = false;
            String[] roles = route.getRoles();

            for (String role : roles) {
                isAllowed = user.hasRole(role);
            }
            return isAllowed;
        }
        throw new ServletException("Access denied exception!");
    }
}
