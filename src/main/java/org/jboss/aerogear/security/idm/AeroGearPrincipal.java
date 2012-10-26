package org.jboss.aerogear.security.idm;

import java.util.Collection;
import java.util.Set;

public interface AeroGearPrincipal {

    boolean hasRoles(Set<String> roles);
    public Collection<String> getRoles();
}
