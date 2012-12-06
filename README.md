# aerogear-security - very lean security API

AeroGear Security will come in two flavors: REST api interfaces and with [AeroGear Controller](https://github.com/aerogear/aerogear-controller) support.

## how to create a new project

### basic use case

1. add the maven dependency

        <dependency>
            <groupId>org.jboss.aerogear</groupId>
            <artifactId>aerogear-security</artifactId>
            <version>1.0.0.M1-20121124-SNAPSHOT</version>
            <scope>compile</scope>
        </dependency>
        
2. AeroGear Security doesn't reinvent the wheel and make use of the existing security providers. For now only PicketBox is supported:

        <dependency>
             <groupId>org.jboss.aerogear</groupId>
             <artifactId>aerogear-security-picketbox</artifactId>
             <version>1.0.0.M1-20121124-SNAPSHOT</version>
             <scope>compile</scope>
        </dependency>


3. add the maven dependency from AeroGear Controller

        <dependency>
             <groupId>org.jboss.aerogear</groupId>
             <artifactId>aerogear-controller</artifactId>
             <version>1.0.0.M1-20121203-SNAPSHOT</version>
             <scope>compile</scope>
        </dependency>
       
## Getting started

For more information about how to create a simple project with AeroGear Controller, please see the [documentation](https://github.com/aerogear/aerogear-controller-demo). 

### Authentication 

1. create a pojo controller and inject *AuthenticationManager*.

        @Stateless
        public class Login {

            @Inject
            private AuthenticationManager authenticationManager;

            public AeroGearUser login(AeroGearUser user) {
 
                authenticationManager.login(user);
 
                return user;
            }

            public void logout() {
                authenticationManager.logout();
            }
        }
       
### Registration

1. create a pojo controller and inject *IdentityManagement*.

        @Stateless
        public class Register {

            //For example purposes only
            public static final String DEFAULT_ROLE = "admin";

            @Inject
            private IdentityManagement configuration;

            public AeroGearUser register(AeroGearUser user) {

                configuration.grant(DEFAULT_ROLE).to(user);
                return user;
            }
        }
       
### Authorization

1. AeroGear Security make use of the *AbstractRoutingModule* to configure role-based authorization support on Controller.

        public class Routes extends AbstractRoutingModule {

           /**
            * Entry point for configuring the routes mapping http requests to the pojo controllers
            */
            @Override
            public void configuration() {
                route()
                    .from("/delorean").roles("admin", "developer")
                    .on(RequestMethod.GET)
                    .to(Home.class).anotherPage();
            }
        } 
       
## RESTful API

1. AeroGear Security also ships with default endpoints working under **/auth** path. Please make sure that JAX-RS was correctly configured.

        @ApplicationPath("/auth")
        public class JaxRsActivator extends Application {
            /* class body intentionally left blank */
        } 

### Endpoints definition

When AeroGear-Security is used as the SecurityProvider for AeroGear-Controller, or possibly deployed standalone, it has a number of endpoints that it exposes which handle different authentication aspects.

### Registering a user ###
```html
http://server:host/myapp/auth/register
```
Response code upon successful registration:
<table>
    <tr><td>Status Code</td><td>Message</td><td>Body</td></tr>
    <tr><td>200</td><td>OK</td><td>The credentials in JSON format. __TODO__ Provide an example here.</td></tr>
</table>
Headers:
<table>
    <tr><td>Header</td><td>Description</td></tr>
    <tr><td>Auth-Token</td><td>The authentication token for the registered and now logged-in user</td></tr>
</table>

Possible Error Responses:
<table>
    <tr><td>Status Code</td><td>Message</td><td>Description</td></tr>
    <tr><td>400</td><td>Bad Request</td><td>The request could not be understood by the server due to malformed syntax.</td></tr>
</table>
### Login ###
```html
http://server:host/myapp/auth/login
```
Response code upon successful login:
<table>
    <tr><td>Status Code</td><td>Message</td><td>Body</td></tr>
    <tr><td>200</td><td>OK</td><td>The credentials in JSON format. __TODO__ Provide an example here.</td></tr>
</table>
Headers:
<table>
    <tr><td>Header</td><td>Description</td></tr>
    <tr><td>Auth-Token</td><td>The authentication token for the registered and now logged-in user</td></tr>
</table>

Possible Error Responses:
<table>
    <tr><td>Status Code</td><td>Message</td><td>Description</td></tr>
    <tr><td>400</td><td>Bad Request</td><td>The request could not be understood by the server due to malformed syntax.</td></tr>
</table>

### Logout ###
```html
http://server:host/myapp/auth/logout
```
Response code upon successful logout:
<table>
    <tr><td>Status Code</td><td>Message</td><td>Body</td></tr>
    <tr><td>200</td><td>OK</td><td>N/A</td></tr>
</table>

Possible Error Responses:
<table>
    <tr><td>Status Code</td><td>Message</td><td>Description</td></tr>
    <tr><td>400</td><td>Bad Request</td><td>The request could not be understood by the server due to malformed syntax.</td></tr>
    <tr><td>401</td><td>Unauthorized</td><td>The request requires user authentication</td></tr>
</table>

## Credits

* The Restful API description was totally based on [@danbev](https://github.com/danbev) documentation https://gist.github.com/4001775. 

---
you can find a slightly better example at <https://github.com/aerogear/aerogear-controller-demo> 