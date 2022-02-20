# Microshop

This is an online shopping application designed with microservices architecture. My main goal is to learn about
microservices, so I kept the domain part as simple as possible. The project is about recording transactions of a
shopping application.

## Run

```bash
sh ./project-configuration/kubernetes/test/start.sh
```

## Architecture

This is the whole application that lives in a Kubernetes cluster.

![image](https://user-images.githubusercontent.com/22731894/154794561-259fd95d-c418-4dd2-9c4f-8c32b0f697ff.png)

## Capabilities

- Read / Write operations
- Synchronous and asynchronous communication
- Circuit breaking
- Horizontal scaling
- Load balancing
- Traffic routing
- Authentication (will be added)

## Run Options

### Development

This mode will start the databases and the message queue in the K8S cluster. You can start the microservices with your
IDE.

`sh ./project-configuration/kubernetes/dev/start.sh`

### Test

This mode will start the whole application in K8S cluster.

`sh ./project-configuration/kubernetes/test/start.sh`

### Production (will be added)

This mode will start the whole application in K8S cluster in a more secure way.<br>
` `

## Entity Relationships

Right now, each microservice has only one table. Their relationships are shown below.

![image](https://user-images.githubusercontent.com/22731894/154796613-16ae1ec1-c081-487b-9307-a077a2911a39.png)

## Synchronous Communication

Let's say we want to retrieve all products that are related to a payment, along with the payment information. Payment
information is in Payment DB and product information is in Inventory DB. To retrieve product information, we need to
send and HTTP request to the inventory microservice. The service (kubernetes component) for inventory microservice act
as a bridge to achieve this goal.

![image](https://user-images.githubusercontent.com/22731894/154794971-b2ef08f4-812c-44b0-ba68-a902fd753030.png)

## Circuit Breaking

Consider the scenario above. If the inventory microservice is down, this will impact payment microservice as well. So we
need to implement the circuit breaker pattern to make microservices less coupled to each other when doing synchronous
communication. This way, even if the inventory service is down, we can still return the payment information.

![image](https://user-images.githubusercontent.com/22731894/154850214-266ffeaf-77b6-47e1-8be8-cfd7ac2db9ec.png)

## Asynchronous Communication

If a payment is created, an order related to the payment must be created as well. Since these two tasks are not directly
connected to each other, we can to this asynchronously. This approach is also another way to provide loose coupling
between microservices. Even if the order microservice is down, the order can be created when it's up again.

![image](https://user-images.githubusercontent.com/22731894/154795436-d415a6c3-f430-4803-a847-25f715bafbe8.png)

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

![image](https://user-images.githubusercontent.com/22731894/154795771-defd449c-31db-4fd3-8fe2-afc3e5cd8913.png)

## Traffic Routing

Let's say we want to list all orders in the system. To do this, we need to send a request to the order microservice.
However, the application has one endpoint and four different microservices. So how can we go to the correct microservice
? This is done by ingress (another kubernetes component). We can define routing rules based on url, and ingress routes
the traffic to the related microservice.

![image](https://user-images.githubusercontent.com/22731894/154796550-d12daf96-9e43-4fed-a52a-1d70845a5a41.png)

## Testing

You can test the application by using
<a href="https://github.com/ahmsay/Microshop/blob/master/microshop.postman_collection.json" target="_blank">
API tests</a> that can be imported into Postman.