package org.jboss.aerogear.security.exception;

import javax.servlet.ServletException;

public class AeroGearSecurityException extends ServletException {

    public AeroGearSecurityException() {
        super();
    }

    public AeroGearSecurityException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
