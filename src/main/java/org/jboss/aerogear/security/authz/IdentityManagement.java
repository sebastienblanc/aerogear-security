/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors
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

package org.jboss.aerogear.security.authz;

import org.jboss.aerogear.security.model.AeroGearUser;

import java.util.List;
import java.util.Set;

/**
 * <i>IdentityManagement</i> allows to assign a set of roles to {@link org.jboss.aerogear.security.model.AeroGearUser} on Identity Manager provider
 */
public interface IdentityManagement<T> {


    /**
     * This method allows to specify which <i>roles</i> must be assigned to {@link org.jboss.aerogear.security.model.AeroGearUser}
     *
     * @param roles The list of roles.
     * @return {@link GrantMethods} is a builder which a allows to apply a list of roles to the specified {@link org.jboss.aerogear.security.model.AeroGearUser}.
     */
    GrantMethods grant(String... roles);

    /**
     * Get an {@link org.jboss.aerogear.security.model.AeroGearUser}
     *
     * @param id
     * @return AeroGearUSer
     */
    AeroGearUser findByUsername(String username) throws RuntimeException;

    AeroGearUser findById(long id) throws RuntimeException;

    /**
     * Remove an {@link org.jboss.aerogear.security.model.AeroGearUser}
     *
     * @param username
     */
    void remove(String username);

    /**
     * Get All the users
     *
     * @param roleName
     * @return
     */
    List<T> findAllByRole(String roleName);

    /**
     * This method creates a new {@link org.jboss.aerogear.security.model.AeroGearUser}
     *
     * @param user
     */
    void create(AeroGearUser user);

    /**
     * <i>GrantMethods</i> is a builder to apply roles to {@link org.jboss.aerogear.security.model.AeroGearUser}
     */
    static interface GrantMethods {
        /**
         * This method applies roles specified on {@link IdentityManagement#grant(String...)}
         *
         * @param user represents a simple user's implementation to hold credentials.
         */
        void to(AeroGearUser user);
    }

    String getSecret();

    String getLogin();

    boolean hasRoles(Set<String> roles);
}
