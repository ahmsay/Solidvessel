# Solidvessel

This is a shopping application designed with microservices architecture. My main goal is to learn about
microservices, so I kept the domain part as simple as possible. The project is about recording transactions of a
shopping application.

## Architecture
### Development
![development](https://user-images.githubusercontent.com/22731894/224532535-234a3ccc-35b3-4ece-ad92-04ae0cff13f7.svg)

In development mode, microservices are running as separate processes on your computer. Databases, RabbitMQ and Redis run
as Docker containers. Since all microservices run on the same machine, they should expose a different port. Therefore, client
must use a specific port for the corresponding microservice.

### Test
![test](https://user-images.githubusercontent.com/22731894/224534722-24fb108b-58f3-447d-af3f-cf53d3d9670a.svg)

In test mode, the whole application lives in a Kubernetes cluster which runs on your computer. Microservices, databases, Redis, and RabbitMQ now
run as deployments or statefulsets.

The communication between components is done through services (another Kubernetes
component, shown as triangles). In terms of scalability, microservices can have more than one instances, called replicas. With Kubernetes, we can easily define
a new replica for a microservice if we need more instances to distribute upcoming traffic. For example account service can have 1 replica and inventory
service can have 3 replicas. Distributing the traffic across the replicas is also done with services.

Since the purpose of the test mode is to simulate the production environment, clients
should be able to access a microservice using a single endpoint. To achieve this goal, there is another K8S component
called ingress. There are routing rules defined in ingress, and it forwards the request made by client to the corresponding 
microservice.

### Production
(details will be added)

## Topics
- <a href=".docs/hexagonal-architecture.md">Hexagonal Architecture<a/>
- <a href=".docs/syncronous-communication.md">Synchronous Communication<a/>
- <a href=".docs/circuit-breaking.md">Circuit Breaking<a/>
- <a href=".docs/asyncronous-communication.md">Asynchronous Communication<a/>
- <a href=".docs/authentication.md">Authentication<a/>
- <a href=".docs/horizontal-scaling.md">Horizontal Scaling<a/>
- <a href=".docs/load-balancing.md">Load Balancing<a/>
- <a href=".docs/traffic-routing.md">Traffic Routing<a/>
- <a href=".docs/shared-module.md">Shared Module<a/>
- Infrastructure as Code
- GitOps
- Going live in minutes

## Run Options

### Development

This mode will start the databases, Redis and RabbitMQ as containers in Docker. You can start the microservices with your
IDE.

```shell
docker-compose up -d
```

### Test

This mode will start the whole application in K8S cluster. You can test it on your own computer if you enable Kubernetes
on Docker Desktop.

```shell
kubectl create ns solidvessel
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.5.1/deploy/static/provider/cloud/deploy.yaml
# Wait for the ingress controller to be ready
kubectl apply -k ./.kubernetes/base
```

### Production
This mode will start the entire application on AWS. You need an AWS account and a user containing necessary permissions to 
launch the application. You need to create a pipeline in CodeBuild and use the files inside ./ci/zero-2-prod folder. Details
will be added later.

## Roadmap
~~- Hexagonal architecture~~
- Domain enrichment
- Data enrichment
- Persistence
- Authorization
- Migration to Kafka
- Security improvements
- Building images on CI environment
