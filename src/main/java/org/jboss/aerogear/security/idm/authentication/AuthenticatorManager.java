package org.jboss.aerogear.security.idm.authentication;

import org.apache.deltaspike.security.api.Identity;
import org.apache.deltaspike.security.api.User;
import org.apache.deltaspike.security.api.credential.Credential;
import org.apache.deltaspike.security.api.credential.LoginCredential;
import org.apache.deltaspike.security.spi.authentication.BaseAuthenticator;

import org.jboss.aerogear.security.idm.authorization.RoleManager;
import org.jboss.aerogear.security.idm.persistence.UserRegistry;
import org.jboss.aerogear.security.idm.util.MessageDigestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class AuthenticatorManager extends BaseAuthenticator {

    @Inject
    private LoginCredential loginCredential;

    @Inject
    private Identity identity;

    @Inject
    private UserRegistry userRegistry;

    @Inject
    private RoleManager roleManager;

    private User user;

    private static final Logger log = LoggerFactory.getLogger(AuthenticatorManager.class);

    @Override
    public void authenticate() {

        org.jboss.aerogear.security.idm.persistence.User userEntity = userRegistry.findBy(this.loginCredential.getUserId());

        if (userEntity != null) {
            setStatus(AuthenticationStatus.SUCCESS);
            this.user = new User(this.loginCredential.getUserId());
            return;
        }

        setStatus(AuthenticationStatus.FAILURE);
    }

    @Override
    public User getUser() {
        return this.user;
    }

    public void login(String userName, final String password) {

        this.loginCredential.setUserId(userName);
        this.loginCredential.setCredential(new Credential<String>() {
            @Override
            public String getValue() {
                return MessageDigestUtil.createDigestPassword(password);
            }
        });

        this.identity.login();
    }

    public boolean isCustomer(User user) {
        return roleManager.hasRole(user.getId());
    }

    public void logout() {
        this.identity.logout();
    }
}
