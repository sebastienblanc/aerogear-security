package org.jboss.aerogear.security.model;

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class AeroGearUserTest {

    @InjectMocks
    private AeroGearUser aeroGearUser = new SampleAeroGearUser();
    @Mock
    private PicketBoxUser picketBoxUser;
    @Mock
    private PicketBoxCDISubject picketBoxSubject;
    @Mock
    private Identity identity;

    private List<String> roleNames = new ArrayList<String>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        roleNames = Arrays.asList("manager", "developer");
        when(picketBoxSubject.getRoleNames()).thenReturn(roleNames);
        when(picketBoxUser.getSubject()).thenReturn(picketBoxSubject);
        when(identity.getUser()).thenReturn(picketBoxUser);
        when(identity.isLoggedIn()).thenReturn(true);
    }

    @Test
    public void testHasRoles() throws Exception {
        Set<String> roles = new HashSet<String>(roleNames);
        assertTrue(aeroGearUser.hasRoles(roles));
    }

    @Test
    public void testNoRoles() throws Exception {
        Set<String> roles = new HashSet<String>(Arrays.asList("guest"));
        assertFalse(aeroGearUser.hasRoles(roles));
    }

    @Test
    public void testUserNotLoggedIn() throws Exception {
        Set<String> roles = new HashSet<String>(roleNames);
        when(identity.isLoggedIn()).thenReturn(false);
        assertFalse(aeroGearUser.hasRoles(roles));
    }

    @Test
    public void testUserIsNull() throws Exception {
        Set<String> roles = new HashSet<String>(roleNames);
        when(identity.getUser()).thenReturn(null);
        assertFalse(aeroGearUser.hasRoles(roles));
    }
}
