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

package org.jboss.aerogear.security.rest.service;

import org.jboss.aerogear.security.auth.AuthenticationManager;
import org.jboss.aerogear.security.auth.CredentialFactory;
import org.jboss.aerogear.security.model.AeroGearCredential;
import org.jboss.aerogear.security.model.AeroGearUser;
import org.jboss.aerogear.security.util.ResponseBuilder;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Stateless
@TransactionAttribute
public class AuthenticationServiceImpl implements AuthenticationService {

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private CredentialFactory credentialFactory;

    @Inject
    private ResponseBuilder responseBuilder;

    public Response login(final AeroGearUser aeroGearUser) {

        credentialFactory.setSimpleCredential(aeroGearUser);
        authenticationManager.login(aeroGearUser);
        return responseBuilder.createResponse();
    }

    public Response otpLogin(final AeroGearUser aeroGearUser) {

        credentialFactory.setOtpCredential(aeroGearUser);
        authenticationManager.login(aeroGearUser);
        return responseBuilder.createResponse();
    }

    public void logout() {
        authenticationManager.logout();
    }

    //TODO token will be provided by servlet filters
    public Response getSecret() {
        return responseBuilder.createResponse();
    }

    public Response getUserInfo() {
        return responseBuilder.createResponse();
    }
}