/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.aerogear.security.model;

import org.jboss.aerogear.security.annotations.LoggedIn;
import org.jboss.aerogear.security.annotations.RoleNames;
import org.jboss.aerogear.security.annotations.SessionId;
import org.picketbox.cdi.PicketBoxIdentity;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Collection;

public class AeroGearCredential implements Serializable {

    @Inject
    private PicketBoxIdentity identity;

    @Produces @LoggedIn
    public String getId(){
        return identity.getUserContext().getUser().getFirstName();
    }

    @Produces @SessionId
    public String getToken() {
        if(identity.isLoggedIn())
            identity.getUserContext().getSession().getId().getId().toString();
        return "";
    }

    @Produces @RoleNames
    public Collection<String> getRoles() {
        return identity.getUserContext().getRoleNames();
    }
}