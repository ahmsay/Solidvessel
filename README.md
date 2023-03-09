# Solidvessel

This is a shopping application designed with microservices architecture. My main goal is to learn about
microservices, so I kept the domain part as simple as possible. The project is about recording transactions of a
shopping application.

## Architecture
### Development
(image will be added)

### Test
![image](https://user-images.githubusercontent.com/22731894/159353041-7dd9dd6c-ae8e-448b-978e-9b59c6464332.svg)
This is the whole application that lives in a Kubernetes cluster.

### Production
(image will be added)

## Topics
- <a href=".docs/syncronous-communication.md">Synchronous Communication<a/>
- <a href=".docs/hexagonal-architecture.md">Hexagonal Architecture<a/>
- <a href=".docs/authentication.md">Authentication<a/>
- <a href=".docs/asyncronous-communication.md">Asynchronous Communication<a/>
- <a href=".docs/circuit-breaking.md">Circuit Breaking<a/>
- <a href=".docs/horizontal-scaling.md">Horizontal Scaling<a/>
- <a href=".docs/load-balancing.md">Load Balancing<a/>
- <a href=".docs/traffic-routing.md">Traffic Routing<a/>
- <a href=".docs/shared-module.md">Shared Module<a/>

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
