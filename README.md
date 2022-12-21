# Microshop

This is an online shopping application designed with microservices architecture. My main goal is to learn about
microservices, so I kept the domain part as simple as possible. The project is about recording transactions of a
shopping application.

## Architecture

![image](https://user-images.githubusercontent.com/22731894/159353041-7dd9dd6c-ae8e-448b-978e-9b59c6464332.svg)

This is the whole application that lives in a Kubernetes cluster.

## Capabilities

- Read / write operations
- Synchronous / asynchronous communication
- Circuit breaking
- Horizontal scaling
- Load balancing
- Traffic routing
- Authentication
- Shared kernel

## Run Options

### Development

This mode will start the databases and the message queue in the K8S cluster. You can start the microservices with your
IDE. Also, you need to manually install the shared module first.

```shell
mvn install -f ./shared/pom.xml
docker-compose -f ./run/dev/docker-compose.yaml up -d
```

### Test

This mode will start the whole application in K8S cluster. You can test it on your own computer if you enable Kubernetes
on Docker Desktop.

```shell
kubectl create ns microshop
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.4.0/deploy/static/provider/cloud/deploy.yaml
# Wait for the ingress controller to be ready
kubectl apply -f ./run/test -R
```

## Entity Relationships

![image](https://user-images.githubusercontent.com/22731894/159342678-8a2f58f0-32c0-43fc-8908-3b34a1fb9e05.svg)

The entity relationships are shown above.

## Synchronous Communication

![image](https://user-images.githubusercontent.com/22731894/159354677-8fb0a2fd-2a42-464a-9b09-6b76d630dfd5.svg)

Let's say we want to retrieve all products that are related to a payment, along with the payment information. Payment
information is in Payment DB and product information is in Inventory DB. To retrieve product information, we need to
send and HTTP request to the inventory microservice. The service (kubernetes component) for inventory microservice act
as a bridge to achieve this goal.

## Circuit Breaking

![image](https://user-images.githubusercontent.com/22731894/159343282-9e3c4b44-8212-4c44-a79f-49336baaf9c6.svg)

Consider the scenario above. If the inventory microservice is down, this will impact payment microservice as well. So we
need to implement the circuit breaker pattern to make microservices less coupled to each other when doing synchronous
communication. This way, even if the inventory service is down, we can still return the payment information.

## Asynchronous Communication

![image](https://user-images.githubusercontent.com/22731894/159354544-46ae0621-cb0a-41f1-b78f-e09f1296db8a.svg)

If a payment is created, an order related to the payment must be created as well. Since these two tasks are not directly
connected to each other, we can to this asynchronously. This approach is also another way to provide loose coupling
between microservices. Even if the order microservice is down, the order can be created when it's up again.

## Horizontal Scaling

We can scale in or out a microservice with `kubectl scale` command.

```bash
kubectl scale --replicas=5 deployment/inventory -n microshop
```

## Load Balancing

![image](https://user-images.githubusercontent.com/22731894/159343897-28494cdb-a70c-4595-83ca-8964a97f5525.svg)

The reason we cannot send a request directly to a microservice is that the microservice may have multiple instances and
each of them will have different endpoints. This is where service (that little triangle) comes in. Service is a
kubernetes component and provides an endpoint to a consumer. It also acts as a load balancer. In the image below, the
request will be received by an appropriate payment microservice.

## Traffic Routing

![image](https://user-images.githubusercontent.com/22731894/159344327-169d07d4-ae79-4b2a-9ed5-2b012bec87cf.svg)

Let's say we want to list all orders in the system. To do this, we need to send a request to the order microservice.
However, the application has one endpoint and four different microservices. So how can we go to the correct microservice
? This is done by ingress (another kubernetes component). We can define routing rules based on url, and ingress routes
the traffic to the related microservice.

## Shared Kernel

Microservices can share a module with common dependencies instead of re-writing same dependencies in all of them. This
might oppose the idea of microservices being fully independent, but it's also useful when you are sure that the shared
code will always be same for all microservices. In this project, extracting the session info functionality and
authentication token classes are in the shared module.

## Authentication

![image](https://user-images.githubusercontent.com/22731894/159349145-ed6a99d6-90a2-4d73-96e4-e5db6f0714ff.svg)

We can use Redis for both caching domain related content and authentication. In this project, all microservices have a
security filter and don't allow a request if it does not contain an authentication info.

To authenticate, user must send a request with username and password info. This request will be received by the
authentication microservice. If credentials are correct, the user session will be saved in Redis. The session also will
be returned as a cookie.

Now we can use this cookie to use the application. For example, when the account microservice receives the request, it
reads the session id from the cookie and tries to find it in Redis. If the session id is stored in Redis and is valid,
then access is granted.

Note: There are Spring Security related token classes in the shared kernel, and they are used by all microservices in
background, for serialization process. Since I couldn't configure the Redis serializer for Spring Security, I decided to
put those classes in the shared kernel and made them have a common package.

## Testing

You can test the application by using
<a href="https://github.com/ahmsay/Microshop/blob/master/microshop.postman_collection.json" target="_blank">
API tests</a> that can be imported into Postman.
