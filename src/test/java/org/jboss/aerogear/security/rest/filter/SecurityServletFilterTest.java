package org.jboss.aerogear.security.rest.filter;

import org.jboss.aerogear.security.authz.AuthorizationManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SecurityServletFilterTest {

    public static final String AUTH_TOKEN = "Auth-Token";

    @InjectMocks
    private SecurityServletFilter tokenServletFilter;

    @Mock
    private HttpServletRequest servletRequest;

    @Mock
    private HttpServletResponse servletResponse;

    @Mock
    private FilterChain filterChain;

    @Mock
    private AuthorizationManager manager;


    @Before
    public void setUp() {
        tokenServletFilter = new SecurityServletFilter();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIgnoreTokenValidationOnAuthPaths() throws Exception {
        when(servletRequest.getRequestURI()).thenReturn("/mysweetapp/auth/login");
        tokenServletFilter.doFilter(servletRequest, servletResponse, filterChain);
        verify(filterChain).doFilter(servletRequest, servletResponse);
    }

    @Test
    public void testTokenValidationOnApplicationPaths() throws Exception {
        String token = "bde58803-fc3b-4c9e-b88d-32a9d5d2ce28";

        when(servletRequest.getRequestURI()).thenReturn("/mysweetapp/projects");
        when(servletRequest.getHeader(AUTH_TOKEN)).thenReturn(token);
        when(manager.validate(token)).thenReturn(true);
        tokenServletFilter.doFilter(servletRequest, servletResponse, filterChain);
        verify(filterChain).doFilter(servletRequest, servletResponse);
    }

    @Test
    public void testDoNotValidateTokenOnLogin() throws Exception {
        String token = null;

        when(servletRequest.getRequestURI()).thenReturn("/mysweetapp/auth/login");
        tokenServletFilter.doFilter(servletRequest, servletResponse, filterChain);
        verify(filterChain).doFilter(servletRequest, servletResponse);
    }

    @Test
    public void testInvalidTokenOnApplicationPaths() throws Exception {
        String token = "guestguestguestguestguest";

        when(servletRequest.getRequestURI()).thenReturn("/mysweetapp/projects");
        when(servletRequest.getHeader(AUTH_TOKEN)).thenReturn(token);
        when(manager.validate(token)).thenReturn(false);
        tokenServletFilter.doFilter(servletRequest, servletResponse, filterChain);
        verify(servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Test
    public void testEmptyTokenOnApplicationPaths() throws Exception {
        String token = "";

        when(servletRequest.getRequestURI()).thenReturn("/mysweetapp/projects");
        when(servletRequest.getHeader(AUTH_TOKEN)).thenReturn(token);
        when(manager.validate(token)).thenReturn(false);
        tokenServletFilter.doFilter(servletRequest, servletResponse, filterChain);
        verify(servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Test
    public void testInvalidTokenOnLogout() throws Exception {
        String token = null;

        when(servletRequest.getRequestURI()).thenReturn("/mysweetapp/auth/logout");
        when(servletRequest.getHeader(AUTH_TOKEN)).thenReturn(token);
        when(manager.validate(token)).thenReturn(false);
        tokenServletFilter.doFilter(servletRequest, servletResponse, filterChain);
        verify(servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
