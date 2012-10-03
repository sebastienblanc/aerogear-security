package org.jboss.aerogear.security.spi;

import org.jboss.aerogear.controller.router.Route;
import org.jboss.picketlink.cdi.Identity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.picketbox.cdi.PicketBoxCDISubject;
import org.picketbox.cdi.PicketBoxUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class AeroGearSecurityProviderTest {

    @Mock
    private Route route;
    @Mock
    private Identity identity;
    @Mock
    private PicketBoxUser picketBoxUser;
    @Mock
    private PicketBoxCDISubject picketBoxSubject;
    @InjectMocks
    private AeroGearSecurityProvider provider = new AeroGearSecurityProvider();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<String> roles = Arrays.asList("manager", "developer");
        when(picketBoxSubject.getRoleNames()).thenReturn(roles);
        when(picketBoxUser.getSubject()).thenReturn(picketBoxSubject);
        when(identity.getUser()).thenReturn(picketBoxUser);
        when(identity.isLoggedIn()).thenReturn(true);

    }

    @Test
    public void testIsRouteAllowed() throws Exception {
        String[] roles = new String[]{"manager"};
        when(route.getRoles()).thenReturn(roles);
        assertTrue(provider.isRouteAllowed(route));
    }

    @Test
    public void testIsRouteNotAllowed() throws Exception {
        String[] roles = new String[]{"guest"};
        when(route.getRoles()).thenReturn(roles);
        assertFalse(provider.isRouteAllowed(route));
    }

    @Test
    public void testUserNotLoggedIn() throws Exception {
        when(identity.isLoggedIn()).thenReturn(false);
        assertFalse(provider.isRouteAllowed(route));
    }
}
