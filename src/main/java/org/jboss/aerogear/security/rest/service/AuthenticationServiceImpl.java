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

import org.abstractj.cuckootp.Totp;
import org.jboss.aerogear.security.auth.AuthenticationManager;
import org.jboss.aerogear.security.auth.CredentialFactory;
import org.jboss.aerogear.security.authz.IdentityManagement;
import org.jboss.aerogear.security.model.AeroGearCredential;
import org.jboss.aerogear.security.model.AeroGearUser;

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
    private IdentityManagement configuration;

    @Inject
    private CredentialFactory credentialFactory;

    @Inject
    private AeroGearCredential aeroGearCredential;

    private static final String HEADER = "Auth-Token";

    //TODO figure out how to provide it
    public static final String DEFAULT_ROLE = "admin";

    public Response login(final AeroGearUser aeroGearUser) {

        credentialFactory.setCredential(aeroGearUser);
        authenticationManager.login(aeroGearUser);
        return Response.ok(aeroGearCredential)
                .header(HEADER, aeroGearCredential.getToken()).build();
    }

    public Response otpLogin(final AeroGearUser aeroGearUser) {

        credentialFactory.setOtpCredential(aeroGearUser);
        authenticationManager.login(aeroGearUser);
        return Response.ok(aeroGearCredential)
                .header(HEADER, aeroGearCredential.getToken()).build();
    }

    //TODO
    public void register(AeroGearUser aeroGearUser) {
        configuration.grant(DEFAULT_ROLE).to(aeroGearUser);
        authenticationManager.login(aeroGearUser);
    }

    public void logout() {
        authenticationManager.logout();
    }

    //TODO token will be provided by servlet filters
    public Response getSecret() {
        Totp totp = new Totp("B2374TNIQ3HKC446");
        AeroGearUser userInfo = new AeroGearUser();
        userInfo.setUri(totp.uri("john"));

        return Response.ok(userInfo)
                .header(HEADER, aeroGearCredential.getToken()).build();
    }

    public Response getUserInfo() {
        Totp totp = new Totp("B2374TNIQ3HKC446");
        AeroGearUser userInfo = new AeroGearUser();
        userInfo.setUri(totp.uri("john"));

        return Response.ok(userInfo)
                .header(HEADER, aeroGearCredential.getToken()).build();
    }
}