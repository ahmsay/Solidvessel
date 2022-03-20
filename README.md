# Microshop

This is an online shopping application designed with microservices architecture. My main goal is to learn about
microservices, so I kept the domain part as simple as possible. The project is about recording transactions of a
shopping application.

## Run

```bash
sh ./kubernetes/test_start.sh
```

## Architecture

This is the whole application that lives in a Kubernetes cluster.

![image](https://user-images.githubusercontent.com/22731894/159161282-eda87a23-454e-45c6-8b1f-0bcbeaf1c6d1.svg)

## Capabilities

- Read / Write operations
- Synchronous and asynchronous communication
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
mvn install -f .\shared\pom.xml
sh ./kubernetes/dev_start.sh
```

### Test

This mode will start the whole application in K8S cluster.

```shell
sh ./kubernetes/test_start.sh
```

## Entity Relationships

Right now, each microservice has only one table. Their relationships are shown below.

![image](https://user-images.githubusercontent.com/22731894/159161952-21bbb747-cd49-47a9-9a33-cea1c6ed73f0.svg)

## Synchronous Communication

Let's say we want to retrieve all products that are related to a payment, along with the payment information. Payment
information is in Payment DB and product information is in Inventory DB. To retrieve product information, we need to
send and HTTP request to the inventory microservice. The service (kubernetes component) for inventory microservice act
as a bridge to achieve this goal.

![image](https://user-images.githubusercontent.com/22731894/159162053-14b727fa-63c3-43c6-8074-d5fc9f9c6c8d.svg)

## Circuit Breaking

Consider the scenario above. If the inventory microservice is down, this will impact payment microservice as well. So we
need to implement the circuit breaker pattern to make microservices less coupled to each other when doing synchronous
communication. This way, even if the inventory service is down, we can still return the payment information.

![image](https://user-images.githubusercontent.com/22731894/159162186-d31a5b34-bd6b-4252-8234-b2fc956f6646.svg)

## Asynchronous Communication

If a payment is created, an order related to the payment must be created as well. Since these two tasks are not directly
connected to each other, we can to this asynchronously. This approach is also another way to provide loose coupling
between microservices. Even if the order microservice is down, the order can be created when it's up again.

![image](https://user-images.githubusercontent.com/22731894/159162245-b3882fbb-8a9b-449b-93d6-954f7575c135.svg)

## Horizontal Scaling

We can scale in or out a microservice with `kubectl scale` command.

```bash
kubectl scale --replicas=5 deployment/inventory -n microshop
```

## Load Balancing

The reason we cannot send a request directly to a microservice is that the microservice may have multiple instances and
each of them will have different endpoints. This is where service (that little green triangle) comes in. Service is a
kubernetes component and provides an endpoint to a consumer. It also acts as a load balancer. In the image below, the
request will be received by an appropriate payment microservice.

![image](https://user-images.githubusercontent.com/22731894/159162368-9cef57e6-bc4c-4fa9-8ffc-b06df5ec3b99.svg)

## Traffic Routing

Let's say we want to list all orders in the system. To do this, we need to send a request to the order microservice.
However, the application has one endpoint and four different microservices. So how can we go to the correct microservice
? This is done by ingress (another kubernetes component). We can define routing rules based on url, and ingress routes
the traffic to the related microservice.

![image](https://user-images.githubusercontent.com/22731894/159162735-680f7069-eb20-4ab3-bd18-d899e979992f.svg)

## Authentication

(details will be added)

## Testing

You can test the application by using
<a href="https://github.com/ahmsay/Microshop/blob/master/microshop.postman_collection.json" target="_blank">
API tests</a> that can be imported into Postman.