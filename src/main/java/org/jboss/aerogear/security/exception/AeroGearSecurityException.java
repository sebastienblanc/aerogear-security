package org.jboss.aerogear.security.exception;

import org.jboss.aerogear.controller.spi.HttpSecurityException;

import javax.servlet.ServletException;

public class AeroGearSecurityException extends ServletException implements HttpSecurityException {

    private int status;

    public AeroGearSecurityException(){}

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
