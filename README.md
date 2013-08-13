# aerogear-security - very lean security API

AeroGear Security already comes with [AeroGear Controller](https://github.com/aerogear/aerogear-controller) support.

## how to create a new project

### basic use case

1. add the maven dependency

        <dependency>
            <groupId>org.jboss.aerogear</groupId>
            <artifactId>aerogear-security</artifactId>
            <version>1.2.1</version>
            <scope>compile</scope>
        </dependency>
        
2. AeroGear Security doesn't reinvent the wheel and make use of the existing security providers. You can choose between [PicketLink](http://www.picketlink.org/) (shown below here), [Apache Shiro](http://shiro.apache.org/) or [Hawk](https://github.com/hueniverse/hawk).

        <dependency>
             <groupId>org.jboss.aerogear</groupId>
             <artifactId>aerogear-security-picketlink</artifactId>
             <version>1.2.1</version>
             <scope>compile</scope>
        </dependency>


3. add the maven dependency from AeroGear Controller

        <dependency>
             <groupId>org.jboss.aerogear</groupId>
             <artifactId>aerogear-controller</artifactId>
             <version>1.0.1</version>
             <scope>compile</scope>
        </dependency>
       
## Getting started

For more information about how to create a simple project with AeroGear Controller, please see the [documentation](https://github.com/aerogear/aerogear-controller-demo). 

### Authentication 

1. create a POJO controller and inject *AuthenticationManager*.

        @Stateless
        public class Login {

            @Inject
            private AuthenticationManager authenticationManager;

            public User login(SimpleUser user) {
 
                authenticationManager.login(user);
 
                return user;
            }

            public void logout() {
                authenticationManager.logout();
            }
        }
       
### Registration

1. create a POJO controller and inject *IdentityManagement*.

        @Stateless
        public class Register {

            //For example purposes only
            public static final String DEFAULT_ROLE = "admin";

            @Inject
            private IdentityManagement configuration;

            public User register(SimpleUser user) {

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
       

## Credits

* More detailed description of the Restful API can be found here: http://aerogear.org/docs/specs/aerogear-rest-api/.

---
you can find a slightly better example at <https://github.com/aerogear/aerogear-controller-demo> 
