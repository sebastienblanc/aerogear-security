package org.jboss.aerogear.security.util;

import org.jboss.aerogear.security.annotations.SecurityStore;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

public class Resources {

//    @Produces
//    @SecurityStore
//    @PersistenceContext(unitName = "security-persistence-unit")
//    private EntityManager entityManager;

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
