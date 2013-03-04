package org.jboss.aerogear.security.filter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StrictTransportFilterTest {

    public static final String STRICT_TRANSPORT_SECURITY = "Strict-Transport-Security";

    @InjectMocks
    private StrictTransportFilter filter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private FilterConfig config;

    @Before
    public void setUp() throws ServletException {
        MockitoAnnotations.initMocks(this);
        when(config.getInitParameter("max-age")).thenReturn("2592000");
        when(config.getInitParameter("include-subdomains")).thenReturn("true");
        when(config.getInitParameter("Location")).thenReturn("https://john.doe");
        filter = new StrictTransportFilter();
        filter.init(config);
    }

    @Test
    public void testNonHttpsRequest() throws Exception {
        when(request.getScheme()).thenReturn("http");
        filter.doFilter(request, response, filterChain);
        verify(response).addHeader("Location", "https://john.doe");
        verify(response).setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
    }

    @Test
    public void testHttpsRequest() throws Exception {
        when(request.getScheme()).thenReturn("https");
        filter.doFilter(request, response, filterChain);
        verify(response).addHeader(STRICT_TRANSPORT_SECURITY, "max-age=2592000; includeSubdomains");
    }
}
