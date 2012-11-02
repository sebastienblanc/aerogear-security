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
import org.jboss.aerogear.security.model.AeroGearUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Before
    public void setUp() throws Exception {
        authenticationService = new AuthenticationServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    private AeroGearUser buildUser(String name, String password){
        AeroGearUser aeroGearUser = new AeroGearUser();
        aeroGearUser.setId(name);
        aeroGearUser.setEmail(name + "@doe.com");
        aeroGearUser.setPassword(password);
        aeroGearUser.setFirstName(name);
        return aeroGearUser;
    }


    @Test
    public void testLogin() throws Exception {
        AeroGearUser john = buildUser("john", "123");
        authenticationService.login(john);
    }

    @Test
    public void testOtpLogin() throws Exception {

    }

    @Test
    public void testLogout() throws Exception {

    }

    @Test
    public void testGetSecret() throws Exception {

    }

    @Test
    public void testGetUserInfo() throws Exception {

    }
}
