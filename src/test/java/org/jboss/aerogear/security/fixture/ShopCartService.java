package org.jboss.aerogear.security.fixture;

import org.jboss.aerogear.security.idm.annotation.CustomSecurityBinding;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ShopCartService {

    @CustomSecurityBinding
    public Car add(Car car) {
        System.out.println("car: " + car.getBrand());
        return car;
    }
}