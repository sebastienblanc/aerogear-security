package org.jboss.aerogear.security.util;

import org.jboss.aerogear.security.model.AeroGearCredential;
import org.jboss.aerogear.security.model.UserInfo;
import org.jboss.aerogear.security.rest.http.AuthenticationRequest;
import org.jboss.aerogear.security.rest.http.AuthenticationResponse;
import org.picketbox.cdi.PicketBoxIdentity;
import org.picketbox.core.UserContext;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Collection;

public class HttpResponseBuilder {

    @Inject
    private PicketBoxIdentity identity;
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
        UserContext userContext = identity.getUserContext();
        return Response.ok(new UserInfo(userContext)).build();
    }

    /**
     * Note: is not recommendable to return username and roles here
     * It will be discussed on M7
     *
     * @return
     */
    public Response createResponse() {

        AeroGearCredential aeroGearCredential = null;
        String token = null;

        if (identity.isLoggedIn()) {
            UserContext userContext = identity.getUserContext();
            token = userContext.getUser().getId();
            Collection<String> roles = userContext.getRoleNames();
            aeroGearCredential = new AeroGearCredential(token, roles);
        }

        return Response.ok(aeroGearCredential).header(AUTH_HEADER, token).build();
    }
}