package org.jboss.aerogear.security.util;

import org.jboss.aerogear.security.rest.http.AeroGearCredential;
import org.jboss.aerogear.security.rest.http.AuthenticationRequest;
import org.jboss.aerogear.security.rest.http.AuthenticationResponse;
import org.jboss.aerogear.security.rest.http.UserInfo;
import org.picketbox.cdi.PicketBoxIdentity;
import org.picketbox.core.UserContext;
import org.picketbox.core.util.Base32;
import org.picketlink.idm.model.Role;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Collection;

public class HttpResponseBuilder {

    private static final String LOGGED = "true";

    @Inject
    private PicketBoxIdentity identity;

    public Response createResponse(AuthenticationRequest authcRequest) {
        AuthenticationResponse response = new AuthenticationResponse();

        response.setId(authcRequest.getId());
        response.setLoggedIn(identity.isLoggedIn());

        if (response.isLoggedIn()) {

            response.setToken(identity.getUserContext().getSession().getId().getId().toString());
        }

        return Response.ok(response).build();
    }

    public Response buildUserInfoResponse(String serialNumber) {
        UserContext userContext = identity.getUserContext();
        UserInfo userInfo = new UserInfo(userContext);

        if (serialNumber != null) {
            userInfo.setSerial(serialNumber);
            userInfo.setB32(Base32.encode(Hex.hexToAscii(serialNumber).getBytes()));
        }

        return Response.ok(userInfo).build();
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
            aeroGearCredential = new AeroGearCredential(token, LOGGED, roles);
        }

        return Response.ok(aeroGearCredential).header("Auth-Token", token).build();
    }
}