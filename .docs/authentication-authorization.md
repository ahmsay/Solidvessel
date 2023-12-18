# Authentication & Authorization

![authentication-authorization](https://github.com/ahmsay/Solidvessel/assets/22731894/5158f601-c9fe-4627-b636-46fdd9a76029)

The diagram above shows the complete picture of authentication and authorization. The API gateway is responsible 
for authentication whereas the microservice is responsible for authorization.

1. Client sends a login request to Keycloak with client and user info.
2. Keycloak validates the request and returns a JWT which contains the user info.
3. Client sends a new request containing the JWT to APISIX, the API gateway.
4. APISIX sends the JWT to Keycloak, so it can validate it. This is done by the openid-connect plugin.
5. Keycloak validates the JWT and returns the result to APISIX.
6. If the JWT is valid, the request is forwarded to the microservice.
7. The microservice has a security filter, and it validates if the user has enough permissions to perform the request.
   The security filter also prevents any invalid request to the microservice in case of someone bypasses the API gateway.
8. If the user's permissions (roles) are sufficient, the request is ready to be processed and then returned to the client.

## Note
- With this setup, your microservices doesn't have to be written with the same framework. You can use any framework or
programming language. Because authentication doesn't depend on your framework or language.
- There is a RoleMapper class in shared module to extract the user roles from JWT. This makes the roles from Keycloak are 
mapped to Spring's authorities properly.
