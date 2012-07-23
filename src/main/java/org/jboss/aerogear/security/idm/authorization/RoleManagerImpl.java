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

package org.jboss.aerogear.security.idm.authorization;

import org.jboss.aerogear.security.idm.persistence.RoleRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

@SessionScoped
public class RoleManagerImpl implements RoleManager {

    private static final Logger log = LoggerFactory.getLogger(RoleManagerImpl.class);
    public static final String CUSTOMER = "customer";


    @Inject
    private RoleRegistry roleRegistry;

    public boolean hasRole(String username) {
        String role = roleRegistry.findBy(username).toString();

        if(CUSTOMER.equals(role)){
            return true;
        } else {
            return false;
        }
    }
}
