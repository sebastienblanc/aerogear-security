package org.jboss.aerogear.security.exception;

import org.jboss.aerogear.controller.spi.HttpStatusAwareException;

public class AeroGearSecurityException extends RuntimeException implements HttpStatusAwareException {

    private int status;

    public AeroGearSecurityException(String message, int status) {
        super(message);
        this.status = status;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
