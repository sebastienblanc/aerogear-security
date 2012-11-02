/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.aerogear.security.exception;

import org.jboss.aerogear.controller.spi.HttpStatusAwareException;

public class AeroGearSecurityException extends RuntimeException implements HttpStatusAwareException {

    private int status;

    public AeroGearSecurityException(HttpStatus httpStatus) {
        super(httpStatus.getMessage());
        this.status = httpStatus.getCode();
    }

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
