/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jboss.aerogear.security.idm.authorization;

import org.apache.deltaspike.security.api.Identity;
import org.apache.deltaspike.security.api.authorization.annotation.Secures;
import org.jboss.aerogear.security.idm.annotation.CustomSecurityBinding;
import org.jboss.aerogear.security.idm.authentication.AuthenticatorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.interceptor.InvocationContext;

@ApplicationScoped
@SuppressWarnings("UnusedDeclaration")
public class CustomAuthorizer {

    private static final Logger log = LoggerFactory.getLogger(CustomAuthorizer.class);

    @Inject
    private Identity identity;

    @Inject
    private AuthenticatorManager authenticatorManager;

    @Secures
    @CustomSecurityBinding
    public boolean doSecuredCheck(InvocationContext invocationContext) throws Exception {
        log.info("============================== CustomAuthorizer ==============================");
        log.info("============================== CustomAuthorizer "
                + invocationContext.getMethod().getName() + " ==============================");
        if (this.identity.isLoggedIn() && authenticatorManager.isCustomer(this.identity.getUser())) {
            log.info("============================== CustomAuthorizer "
                    + authenticatorManager.isCustomer(this.identity.getUser())
                    + " ==============================");
            return true;
        } else {
            return false;
        }
    }
}
