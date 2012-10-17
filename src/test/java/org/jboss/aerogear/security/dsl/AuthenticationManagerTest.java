package org.jboss.aerogear.security.dsl;

import org.jboss.aerogear.security.auth.AuthenticationManager;
import org.jboss.aerogear.security.auth.AuthenticationManagerImpl;
import org.jboss.aerogear.security.exception.AeroGearSecurityException;
import org.jboss.aerogear.security.model.AeroGearUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.picketbox.cdi.PicketBoxIdentity;
import org.picketlink.credential.LoginCredentials;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AuthenticationManagerTest {

    @Mock
    private AeroGearUser aeroGearUser;
    @Mock
    private PicketBoxIdentity picketBoxIdentity;
    @Mock
    private LoginCredentials loginCredentials;

    @InjectMocks
    private AuthenticationManager authenticationManager = new AuthenticationManagerImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin() throws Exception {
        when(picketBoxIdentity.isLoggedIn()).thenReturn(true);
        assertTrue(authenticationManager.login(aeroGearUser));
    }

    @Test(expected = AeroGearSecurityException.class)
    public void testInvalidLogin() throws Exception {
        when(picketBoxIdentity.isLoggedIn()).thenReturn(false);
        authenticationManager.login(aeroGearUser);
    }

    @Test
    public void testLogout() throws Exception {
        when(picketBoxIdentity.isLoggedIn()).thenReturn(true);
        authenticationManager.logout();
        verify(picketBoxIdentity).logout();
    }
}
