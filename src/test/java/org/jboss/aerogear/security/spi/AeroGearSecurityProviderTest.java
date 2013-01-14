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

package org.jboss.aerogear.security.spi;

import org.jboss.aerogear.controller.router.Route;
import org.jboss.aerogear.security.exception.AeroGearSecurityException;
import org.jboss.aerogear.security.idm.AeroGearCredential;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

public class AeroGearSecurityProviderTest {

    @Mock
    private Route route;
    @Mock
    private AeroGearCredential credential;
    @InjectMocks
    private AeroGearSecurityProvider provider = new AeroGearSecurityProvider();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Set<String> roles = new HashSet<String>(Arrays.asList("manager", "developer"));
        when(route.getRoles()).thenReturn(roles);
        when(credential.hasRoles(roles)).thenReturn(true);
    }

    @Test
    public void testIsRouteAllowed() throws Exception {
        provider.isRouteAllowed(route);
    }

    @Test(expected = AeroGearSecurityException.class)
    public void testIsRouteNotAllowed() throws Exception {
        Set<String> roles = new HashSet<String>(Arrays.asList("guest"));
        when(route.getRoles()).thenReturn(roles);
        provider.isRouteAllowed(route);
    }
}
