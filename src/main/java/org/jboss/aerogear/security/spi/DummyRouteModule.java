package org.jboss.aerogear.security.spi;

import org.jboss.aerogear.controller.router.AbstractRoutingModule;

import javax.enterprise.inject.Alternative;

@Alternative
public class DummyRouteModule extends AbstractRoutingModule {

    @Override
    public void configuration() throws Exception {
        //Dummy implementation fallback
    }
}
