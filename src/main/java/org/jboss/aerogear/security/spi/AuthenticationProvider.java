package org.jboss.aerogear.security.spi;

import org.apache.deltaspike.security.api.authorization.annotation.Secures;
import org.jboss.aerogear.controller.router.Route;
import org.jboss.aerogear.controller.spi.SecurityProvider;
import org.jboss.aerogear.security.idm.annotation.CustomSecurityBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthenticationProvider implements SecurityProvider {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationProvider.class);

//    @Inject
//    private Identity identity;
//
//    @Inject
//    private AuthenticatorManager authenticatorManager;

    @Secures
    @CustomSecurityBinding
    public boolean isRouteAllowed(Route route) {

        log.info("============================== AuthenticationProvider ==============================");

        //route.getRoles();

        //log.info("============================== " + route.getRoles().length + " ==============================");


        return false;
    }
}
