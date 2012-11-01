package org.jboss.aerogear.security.model;

import java.util.Collection;

public interface AeroGearCredential {

    public String getId();

    public String getKey();

    public String getSecret();

    public String getB32();

    public String getToken();

    public Collection<String> getRoles();

}
