package org.jboss.aerogear.security.idm.annotation;

import org.apache.deltaspike.security.api.authorization.annotation.SecurityBindingType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@SecurityBindingType
public @interface CustomSecurityBinding {

}
