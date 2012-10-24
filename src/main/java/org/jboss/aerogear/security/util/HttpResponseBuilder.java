package org.jboss.aerogear.security.util;

import org.jboss.aerogear.security.api.annotations.SessionToken;
import org.jboss.aerogear.security.impl.model.AeroGearCredential;
import org.jboss.aerogear.security.impl.model.Secret;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

public class HttpResponseBuilder {

    @Inject
    private AeroGearCredential credentials;
    @Inject
    private Secret secret;
    @Inject
    @SessionToken
    private String token;

    private static final String AUTH_HEADER = "Auth-Token";

    public Response createResponse() {
        return Response.ok(credentials).header(AUTH_HEADER, token).build();
    }

    public Response buildSecretUserInfoResponse() {
        Secret userSecret = secret.generate();
        return Response.ok(userSecret).header(AUTH_HEADER, token).build();
    }
}