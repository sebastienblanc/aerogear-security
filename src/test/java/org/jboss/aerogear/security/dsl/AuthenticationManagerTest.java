package org.jboss.aerogear.security.dsl;

import org.jboss.aerogear.security.exception.AeroGearSecurityException;
import org.jboss.aerogear.security.model.AeroGearUser;
import org.jboss.picketlink.cdi.Identity;
import org.jboss.picketlink.cdi.credential.LoginCredentials;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthenticationManagerTest {

    @Mock
    private AeroGearUser aeroGearUser;
    @Mock
    private LoginCredentials loginCredentials;
    @Mock
    private Identity identity;

    @InjectMocks
    private AuthenticationManager authenticationManager = new AuthenticationManagerImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin() throws Exception {
        when(identity.isLoggedIn()).thenReturn(true);
        assertTrue(authenticationManager.login(aeroGearUser));
    }

    @Test(expected = AeroGearSecurityException.class)
    public void testInvalidLogin() throws Exception {
        when(identity.isLoggedIn()).thenReturn(false);
        authenticationManager.login(aeroGearUser);
    }

    @Test
    public void testLogout() throws Exception {
        when(identity.isLoggedIn()).thenReturn(true);
        authenticationManager.logout();
        verify(identity).logout();
    }
}
