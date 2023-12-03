# API Gateway

The main idea of using an API Gateway in a microservices architecture is to serve the clients from a single
endpoint. This approach overcomes the challenge of dealing with different urls of
microservices. Furthermore, API Gateways have more capabilities such as rate limiting, canary releases and
authentication.
The API Gateway used in this project is Apache APISIX.

![api_gateway](https://github.com/ahmsay/Solidvessel/assets/22731894/8a1c98c8-1c57-4689-8f17-68c180b82934)

The diagram above shows how the traffic routing works. APISIX reads from a configuration file where the routing rules
are written,
and routes the traffic according to those rules. For example, when the url includes /order prefix, APISIX will route the
request
to the order microservice.

## Note for Development and Test Environments

In development and test environments, <b>the container must run in the host network</b>. This is
because;

<b>For development environment:</b> When you start microservices with your IDE, they run in the host network (your own network). So in order to route the
traffic to the microservices, APISIX must be in the same network as well.

<b>For test environment:</b> When APISIX needs to send a request to Keycloak for authentication, the url of Keycloak must be both accessible
within the container and from the browser. That's why the url of Keycloak is localhost and not the ClusterIp service address. However when the
url is localhost, APISIX thinks it is the localhost of the container, not the host machine. To resolve this, I needed APISIX to run in
host network.

(This just informative, everything is already
configured properly.)