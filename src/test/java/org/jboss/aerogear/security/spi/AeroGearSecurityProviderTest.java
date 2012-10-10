package org.jboss.aerogear.security.spi;

import org.jboss.aerogear.controller.router.Route;
import org.jboss.aerogear.security.exception.AeroGearSecurityException;
import org.jboss.picketlink.cdi.Identity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.picketbox.cdi.PicketBoxCDISubject;
import org.picketbox.cdi.PicketBoxUser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        Set<String> roles = new HashSet<String>(Arrays.asList("manager"));
        when(route.getRoles()).thenReturn(roles);
        provider.isRouteAllowed(route);
    }

    @Test(expected = AeroGearSecurityException.class)
    public void testIsRouteNotAllowed() throws Exception {
        Set<String> roles = new HashSet<String>(Arrays.asList("guest"));
        when(route.getRoles()).thenReturn(roles);
        provider.isRouteAllowed(route);
    }

    @Test(expected = AeroGearSecurityException.class)
    public void testUserNotLoggedIn() throws Exception {
        when(identity.isLoggedIn()).thenReturn(false);
        provider.isRouteAllowed(route);
    }
}
