package org.jboss.aerogear.security.spi;

import org.jboss.aerogear.controller.router.Route;
import org.jboss.aerogear.controller.spi.SecurityProvider;
import org.jboss.picketlink.cdi.Identity;
import org.picketbox.cdi.PicketBoxUser;

import javax.inject.Inject;
import javax.servlet.ServletException;
import java.util.Arrays;
import java.util.List;

//TODO must be improved
public class AeroGearSecurityProvider implements SecurityProvider {

    @Inject
    private Identity identity;

    @Override
    public boolean isRouteAllowed(Route route) throws ServletException {

        System.out.println("Security provider");

        if (identity.isLoggedIn()) {

            PicketBoxUser user = (PicketBoxUser) identity.getUser();
            List<String> roles = Arrays.asList(route.getRoles());
            return user.getSubject().getRoleNames().containsAll(roles);
        }
        return false;
    }
}
