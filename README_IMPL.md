Notes:

./gradlew eclipse : to generate Eclipse related files
./gradlew clEclEcl EclCl assemble : to generate eclipse related class-path
./gradlew clean build : run tests and build

Now the project is ready to be imported into Eclipse.

### JPA data access implementation and data layer

The domain package contains the model classes and relations.

### Make the application configurable

The properties are available inside `application.properties`. Also the `ApplicationConfiguration`
imports these properties as maps which are used throughout the application.

### Implement the provided `OpenApi` specification.

REST APIs are exposed at
1. `/locations`
2. `/locations/<type>/<isocode>`

### Create a client

A rest client is exposed at `/resttemplate`. When an HTTP GET call is done to
`/resttemplate` it uses a rest client to connect to the `/locations/country/US`
and returns the result.

Security has been configured for the `/locations/**` apis as 'someuser'/'psw'.
The actuator urls at `/actuator/metrics/**` have been secured with 'ops'/'psw'

### Add statistics for your backend

The actuator apis are available at port `7070` so that it doesn't affect the user-experience.

Some of the things that can be done :

http://localhost:8080/actuator/metrics/http.server.requests  (to get the number of requests)
http://localhost:8080/actuator/metrics/http.server.requests?tag=status:404 (number of requests with 404 status)
http://localhost:8080/actuator/metrics/http.server.requests?measurement=statistic:COUNT

### Bonus points
#### correlation id
The logging has been switched on in the `logback-spring.xml` which shows the correlation-id
being assigned to every single activity from request to response. This logging has been
switched on for demo purposes. This can be switched off by changing the logging level
of the logger `com.afkl` to info or higher.
