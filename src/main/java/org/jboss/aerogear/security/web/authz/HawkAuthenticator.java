/**
 * JBoss, Home of Professional Open Source
 *  Copyright Red Hat, Inc., and individual contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.jboss.aerogear.security.web.authz;

import com.google.common.collect.ImmutableMap;
import com.google.common.io.CharStreams;
import com.wealdtech.hawk.Hawk;
import com.wealdtech.hawk.HawkCredentials;
import com.wealdtech.hawk.HawkServer;
import org.jboss.aerogear.security.auth.HawkCredentialProvider;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * <i>HawkAuthenticator</i> Authenticate a Hawk request
 */
public class HawkAuthenticator {

    private static final String SERVER_AUTHORIZATION = "Server-Authorization";
    private static final String HOST = "Host";
    private static final String ENCODING = "UTF-8";
    private static final String AUTHORIZATION = "Authorization";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String CALCULATED_HASH = "hash";

    private HawkCredentialProvider credentialProvider;

    private HawkServer server;

    public HawkAuthenticator() {
    }

    /**
     * @param server   Hawk HTTP configuration provided by the framework
     * @param provider developer's credential provider implementation
     */
    @Inject
    public HawkAuthenticator(HawkServer server, HawkCredentialProvider provider) {
        this.credentialProvider = provider;
        this.server = server;
    }

    /**
     * @param request  Hawk HTTP <i>Authorization</i> request
     * @param response Hawk HTTP response to authenticated requests with a <i>Server-Authorization</i> header
     * @throws Exception
     */
    public void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String hash = null;

        try {
            URI uri = getUri(request);

            ImmutableMap<String, String> authorizationHeaders = server.splitAuthorizationHeader(request.getHeader(AUTHORIZATION));

            HawkCredentials credentials = credentialProvider.findByKey(authorizationHeaders.get("id"));

            if (authorizationHeaders.get(CALCULATED_HASH) != null) {
                hash = Hawk.calculateMac(credentials, CharStreams.toString(new InputStreamReader(request.getInputStream(), ENCODING)));
            }
            server.authenticate(credentials, uri, request.getMethod(), authorizationHeaders, hash, hasBody(request));
            addAuthenticateHeader(response);


        } catch (Throwable de) {
            throw new Exception(de);
        }
    }

    private boolean hasBody(HttpServletRequest request) {
        return request.getHeader(CONTENT_LENGTH) != null;
    }

    private URI getUri(HttpServletRequest request) throws Exception {
        URI uri;
        try {
            uri = new URI(request.getScheme() + "://" + request.getHeader(HOST) + request.getRequestURI());
        } catch (URISyntaxException use) {
            throw new Exception(use);
        }
        return uri;
    }

    private void addAuthenticateHeader(final HttpServletResponse response) {
        String authenticate = server.generateAuthenticateHeader();
        List<String> header = new ArrayList<String>();
        header.add(authenticate);
        response.addHeader(SERVER_AUTHORIZATION, header.toString());
    }
}
