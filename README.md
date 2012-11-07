# aerogear-security - very lean security interface

AeroGear Security will come in two flavors: REST api interfaces and with [AeroGear Controller](https://github.com/aerogear/aerogear-controller) support.

## how to create a new project

### basic use case

1. add the maven dependency

       <dependency>
           <groupId>org.jboss.aerogear</groupId>
           <artifactId>aerogear-security</artifactId>
           <version>1.0.0.M1-20121107</version>
           <scope>compile</scope>
       </dependency>
        
2. AeroGear Security doesn't reinvent the wheel and make use of the existing security providers. For now only PicketBox is supported:

       <dependency>
            <groupId>org.jboss.aerogear</groupId>
            <artifactId>aerogear-security-picketbox</artifactId>
            <version>1.0.0.M1-20121107</version>
            <scope>compile</scope>
       </dependency>


3. add the maven dependency from AeroGear Controller

       <dependency>
            <groupId>org.jboss.aerogear</groupId>
            <artifactId>aerogear-controller</artifactId>
            <version>0.0.4.M6-SNAPSHOT</version>
            <scope>compile</scope>
       </dependency>
       
## Getting started

For more information about how to create a simple project with AeroGear Controller, please see the [documentation](https://github.com/aerogear/aerogear-controller-demo). 

## Authentication 

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
       
## Registration

1. create a pojo controller and inject *AuthenticationManager*.

1. create a jsp page at `/WEB-INF/pages/<Controller Class Name>/<method>.jsp`

        <!-- /WEB-INF/pages/Home/index.jsp -->
        <html>
            <body>
                <p>hello from index!</p>
            </body>
        </html>
        
### parameter population

You can use immutable beans straight away as controller parameters:

        public class Store {
            public Car save(Car car) {
                return car;
            }
        }

This can be populated by putting a route to it (preferrably via post, of course)

        route()
            .from("/cars")
            .on(RequestMethod.POST)
            .to(Store.class).save(param(Car.class));


And you can use a simple html form for it, by just following the convention:

            <input type="text" name="car.color"/>
            <input type="text" name="car.brand"/>

The car object will be automatically populated with the provided values - note that it supports deep linking, so this would work fine too:

            <input type="text" name="car.brand.owner"/>

All the intermediate objects are created automatically.

---
you can find a slightly better example at <https://github.com/aerogear/aerogear-controller-demo> 