# Authentication

![authentication](https://github.com/ahmsay/Solidvessel/assets/22731894/5158f601-c9fe-4627-b636-46fdd9a76029)

The diagram above shows the complete picture of both authentication and authorization. The API gateway is responsible 
for authentication whereas the microservice is responsible for authorization. The authentication part is the
first 5 steps.

1. Client sends a login request to Keycloak with client and user info.
2. Keycloak validates the request and returns a JWT which contains the user info.
3. Client sends a new request containing the JWT to APISIX, the API gateway.
4. APISIX validates the JWT by using the openid-connect plugin. The plugin makes APISIX connect to Keycloak and perform 
necessary validations.
5. If the JWT is valid, the request is ready to be forwarded.