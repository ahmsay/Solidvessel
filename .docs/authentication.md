## Authentication

![authentication](https://user-images.githubusercontent.com/22731894/226122649-ed57b893-0c05-4dcf-a515-e3231a8c0790.svg)

We can use Redis for both caching domain related content and authentication. In this project, all microservices have a
security filter and don't allow a request if it does not contain an authentication info.

To authenticate, user must send a request with username and password info. This request will be received by the
authentication microservice. If credentials are correct, the user session will be saved in Redis. The session also will
be returned as a cookie.

Now we can use this cookie to use the application. For example, when the account microservice receives the request, it
reads the session id from the cookie and tries to find it in Redis. If the session id is stored in Redis and is valid,
then access is granted.

Note: There are Spring Security related token classes in the shared module, and they are used by all microservices in
background, for serialization process. Since I couldn't configure the Redis serializer for Spring Security, I decided to
put those classes in the shared module and made them have a common package.
