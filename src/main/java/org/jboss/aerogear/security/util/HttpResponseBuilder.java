package org.jboss.aerogear.security.util;

import org.jboss.aerogear.security.rest.http.AuthenticationRequest;
import org.jboss.aerogear.security.rest.http.AuthenticationResponse;
import org.jboss.aerogear.security.rest.http.UserInfo;
import org.picketbox.cdi.PicketBoxIdentity;
import org.picketbox.core.UserContext;
import org.picketbox.core.util.Base32;

import javax.inject.Inject;

public class HttpResponseBuilder {

    @Inject
    private PicketBoxIdentity identity;

    public AuthenticationResponse createResponse(AuthenticationRequest authcRequest) {
        AuthenticationResponse response = new AuthenticationResponse();

        response.setId(authcRequest.getId());
        response.setLoggedIn(identity.isLoggedIn());

        if (response.isLoggedIn()) {

            response.setToken(identity.getUserContext().getSession().getId().getId().toString());
        }

        return response;
    }

    public UserInfo buildUserInfoResponse(String serialNumber) {
        UserContext userContext = identity.getUserContext();
        UserInfo userInfo = new UserInfo(userContext);

        if (serialNumber != null) {
            userInfo.setSerial(serialNumber);
            userInfo.setB32(Base32.encode(Hex.hexToAscii(serialNumber).getBytes()));
        }

        return userInfo;
    }

    public UserInfo buildUserInfoResponse() {
        UserContext userContext = identity.getUserContext();
        return new UserInfo(userContext);
    }
}