/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.aerogear.security.rest.service;

import org.jboss.aerogear.security.auth.AuthenticationManager;
import org.jboss.aerogear.security.model.AeroGearCredential;
import org.jboss.aerogear.security.model.AeroGearUser;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Stateless
@TransactionAttribute
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final String HEADER = "Auth-Token";

    @Inject
    private AuthenticationManager authenticationManager;

    public Response login(final AeroGearUser aeroGearUser) {

        authenticationManager.login(aeroGearUser);
        return Response.ok(authenticationManager.getCredential()).build();
    }

    public Response otpLogin(final AeroGearUser aeroGearUser) {

        authenticationManager.login(aeroGearUser);
        return Response.ok(authenticationManager.getCredential()).build();
    }

    public void logout() {
        authenticationManager.logout();
    }

    //TODO token will be provided by servlet filters
    public Response getSecret() {
        AeroGearCredential credential = authenticationManager.getCredential();
        return Response.ok(credential)
                .header(HEADER, credential.getToken()).build();
    }

    public Response getUserInfo() {
        AeroGearCredential credential = authenticationManager.getCredential();
        return Response.ok(credential)
                .header(HEADER, credential.getToken()).build();
    }
}