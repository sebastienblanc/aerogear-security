package org.jboss.aerogear.security.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.picketbox.cdi.PicketBoxIdentity;
import org.picketbox.core.UserContext;
import org.picketlink.idm.model.User;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class HttpResponseBuilderTest {

    @Mock
    private PicketBoxIdentity identity;
    @Mock
    private UserContext userContext;
    @Mock
    private User user;

    @InjectMocks
    private HttpResponseBuilder builder = new HttpResponseBuilder();

    @Before
    public void setUp() throws Exception {
        Collection<String> roleNames = Arrays.asList(new String[]{"admin", "developer"});
        MockitoAnnotations.initMocks(this);
        when(user.getId()).thenReturn("733a1408-ed8b-41d0-922e-90794fa6bc28");
        when(userContext.getUser()).thenReturn(user);
        when(identity.isLoggedIn()).thenReturn(true);
        when(identity.getUserContext()).thenReturn(userContext);
        when(userContext.getRoleNames()).thenReturn(roleNames);
    }



    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testBuildUserInfoResponse() throws Exception {
        assertNotNull(builder.createResponse());
    }

    @Test
    public void testCreateResponse() throws Exception {

    }
}
