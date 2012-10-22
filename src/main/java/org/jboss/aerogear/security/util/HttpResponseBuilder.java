package org.jboss.aerogear.security.util;

import org.jboss.aerogear.security.model.AeroGearCredential;
import org.jboss.aerogear.security.rest.http.AuthenticationRequest;
import org.jboss.aerogear.security.rest.http.AuthenticationResponse;
import org.picketbox.cdi.PicketBoxIdentity;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class HttpResponseBuilder {

    @Inject
    private PicketBoxIdentity identity;
    @Inject
    private AeroGearCredential credentials;

    @Inject
    private Secret secret;

    private static final String AUTH_HEADER = "Auth-Token";

    public Response createResponse(AuthenticationRequest authcRequest) {
        AuthenticationResponse response = new AuthenticationResponse();

        response.setId(authcRequest.getId());
        response.setLoggedIn(identity.isLoggedIn());

        if (response.isLoggedIn()) {

            response.setToken(identity.getUserContext().getSession().getId().getId().toString());
        }

        return Response.ok(response).build();
    }

    public Response buildSecretUserInfoResponse() {

        Secret userSecret = secret.generate();

        return Response.ok(secret).build();
    }

    public Response buildUserInfoResponse() {
        return Response.ok(credentials).build();
    }

    /**
     * Note: is not recommendable to return username and roles here
     * It will be discussed on M7
     *
     * @return
     */
    public Response createResponse() {

        if (identity.isLoggedIn()) {
            return Response.ok(credentials).header(AUTH_HEADER, credentials.getToken()).build();
        }
        return null;
    }
}