package org.jboss.aerogear.security.service;

import org.apache.deltaspike.security.impl.authorization.SecurityInterceptor;
import org.jboss.aerogear.security.fixture.Car;
import org.jboss.aerogear.security.fixture.Resources;
import org.jboss.aerogear.security.fixture.ShopCartService;
import org.jboss.aerogear.security.idm.annotation.CustomSecurityBinding;
import org.jboss.aerogear.security.idm.authentication.AuthenticatorManager;
import org.jboss.aerogear.security.idm.authorization.CustomAuthorizer;
import org.jboss.aerogear.security.idm.authorization.RoleManager;
import org.jboss.aerogear.security.idm.authorization.RoleManagerImpl;
import org.jboss.aerogear.security.idm.persistence.Role;
import org.jboss.aerogear.security.idm.persistence.RoleRegistry;
import org.jboss.aerogear.security.idm.persistence.User;
import org.jboss.aerogear.security.idm.persistence.UserRegistry;
import org.jboss.aerogear.security.util.ArchiveUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(Arquillian.class)
public class ShopCartServiceIT {

    @Inject
    private UserRegistry userRegistry;

    @Inject
    private RoleRegistry roleRegistry;

    @Inject
    private AuthenticatorManager authenticatorManager;

    @Inject
    private ShopCartService shopCartService;

    public Role buildRole(String roleName) {
        Role role = new Role(roleName, roleName);
        roleRegistry.newRole(role);
        return role;
    }

    @Deployment
    public static WebArchive createDeployment() {

        return ShrinkWrap.create(WebArchive.class)
                .addAsLibraries(ArchiveUtils.getDeltaSpikeCoreAndSecurityArchive())
                .addClasses(ShopCartService.class, Resources.class, CustomAuthorizer.class,
                        SecurityInterceptor.class, AuthenticatorManager.class,
                        Role.class, User.class, Car.class, RoleManager.class, RoleManagerImpl.class,
                        CustomSecurityBinding.class, CustomAuthorizer.class,
                        RoleRegistry.class, UserRegistry.class)
                .addAsWebInfResource(ArchiveUtils.getBeansXml(), "beans.xml")
                .addAsResource("persistence.xml", "META-INF/persistence.xml");

    }

    @Test
    public void shouldAccessProtectedResourceWithValidLogin() throws Exception {
        try {
            User user = new User("test", "test");
            user.setRoles(buildRole("customer"));
            userRegistry.newUser(user);
            authenticatorManager.login("test", "test");
            shopCartService.add(new Car("red", "camaro"));
        } catch (Exception e) {
            fail("Request failed");
        }
    }

    @Test
    public void shouldThrowExceptionWhenRoleInvalid() throws Exception {
        try {
            User user = new User("test2", "test2");
            user.setRoles(buildRole("manager"));
            userRegistry.newUser(user);
            //TODO must be replaced
            authenticatorManager.login("john", "doe");
            shopCartService.add(new Car("chevelle", "ss396"));
            fail("Should throw authorization exception");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void shouldThrowExceptionWithoutValidLogin() throws Exception {
        try {
            shopCartService.add(new Car("red", "camaro"));
            fail("Should throw authorization exception");
        } catch (Exception e) {
            assertTrue(true);
        }
    }
}
