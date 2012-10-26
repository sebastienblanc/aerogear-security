package org.jboss.aerogear.security.auth;

import org.jboss.aerogear.security.util.Hex;

import java.util.UUID;

public class AuthenticationSecretKeyCode {

    public static String create() {
        String secret = UUID.randomUUID().toString();
        secret = secret.replace('-', 'c');

        //Just pick the first 10 characters
        secret = secret.substring(0, 10);

        secret = Hex.toHexString(secret.getBytes());

        return secret;
    }
}