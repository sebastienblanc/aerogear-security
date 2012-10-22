package org.jboss.aerogear.security.util;

import org.jboss.aerogear.security.model.AeroGearCredential;
import org.jboss.aerogear.security.model.Secret;
import org.jboss.aerogear.security.rest.http.AuthenticationRequest;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class HttpResponseBuilder {

    @Inject
    private AeroGearCredential credentials;
    @Inject
    private Secret secret;

    private static final String AUTH_HEADER = "Auth-Token";

    public Response createResponse(AuthenticationRequest authcRequest) {
        return Response.ok(credentials).build();
    }

    public Response buildSecretUserInfoResponse() {

        Secret userSecret = secret.generate();

        return Response.ok(secret).build();
    }

    public Response buildUserInfoResponse() {
        return Response.ok(credentials).header(AUTH_HEADER, credentials.getId()).build();
    }

    /**
     * Note: is not recommendable to return username and roles here
     * It will be discussed on M7
     *
     * @return
     */
    public Response createResponse() {
        return Response.ok(credentials).header(AUTH_HEADER, credentials.getId()).build();
    }
}