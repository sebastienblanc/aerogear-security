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

import org.picketbox.cdi.PicketBoxIdentity;
import org.picketlink.idm.model.AbstractIdentityType;
import org.picketlink.idm.model.User;

import javax.inject.Inject;
import java.util.Set;

public abstract class AeroGearUser extends AbstractIdentityType implements User {

    @Inject
    private PicketBoxIdentity identity;

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String otp;

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return String.format("%s%s", KEY_PREFIX, id);
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public abstract String getPassword();
    public abstract void setPassword(String password);

    public boolean hasRoles(Set<String> roles) {

        boolean hasRoles = false;

        if (identity.isLoggedIn()) {
            hasRoles = identity.getUserContext().getRoleNames().containsAll(roles);
        }

        return hasRoles;
    }

}