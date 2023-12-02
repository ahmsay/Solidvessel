# API Gateway - WILL BE UPDATED

The main idea of using an API Gateway in a microservices architecture is to serve the clients from a single
endpoint. This approach overcomes the challenge of dealing with different urls of
microservices. Furthermore, API Gateways have more capabilities such as rate limiting, canary releases and
authentication.
The API Gateway used in this project is Apache APISIX.

![api_gateway](https://github.com/ahmsay/Solidvessel/assets/22731894/5707e2de-298a-45b4-8c50-808d9d3a94dd)

The diagram above shows how the traffic routing works. APISIX reads from a configuration file where the routing rules
are written,
and routes the traffic according to those rules. For example, when the url includes /order prefix, APISIX will route the
request
to the order microservice.

## Note for the Development Environment

APISIX not only works in a K8S cluster, it also works as a standalone Docker container. In the development mode, the
configuration
is pretty much the same, except one important difference: <b>the container must run in the host network</b>. This is
because
when you start microservices with your IDE, they run in the host network (your own network). So in order to route the
traffic
to the microservices, APISIX must be in the same network as well. (This just informative, everything is already
configured properly.)