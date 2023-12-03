# Authorization

![authentication](https://github.com/ahmsay/Solidvessel/assets/22731894/5158f601-c9fe-4627-b636-46fdd9a76029)

The diagram above shows the complete picture of both authentication and authorization. The API gateway is responsible
for authentication whereas the microservice is responsible for authorization. The authorization part starts from 6th step.

6. After JWT is validated, the request is forwarded to the microservice.
7. The microservice has a security filter, and it validates if the user has enough permissions to perform the request.
The security filter also prevents any invalid request to the microservice in case of someone bypasses the API gateway.
8. If the user's permissions (roles) are sufficient, the request is ready to be processed and then returned to the client.

## Note
There is a RoleMapper class in shared module to extract the user roles from JWT. This makes the roles from Keycloak are
mapped to Spring's authorities properly.