# Solidvessel

This is a shopping application designed with microservices architecture. My main goal is to learn about
microservices, so I kept the domain part as simple as possible. The project is about recording transactions of a shopping application.
<a href=".docs/run-options.md">Check here to see how to run.<a/>

## Architecture
### Development
![development](https://github.com/ahmsay/Solidvessel/assets/22731894/790cf8c2-88db-44df-8512-be3fdc5397a8)

In development mode, microservices are running as separate processes on your computer. The API Gateway, databases,
RabbitMQ and Redis run
as Docker containers. Since all microservices run on the same device, they should expose a different port. The
communication between client
and microservices is done through the API Gateway.

### Test

![test](https://github.com/ahmsay/Solidvessel/assets/22731894/24dc7b65-b19d-4632-9c26-ec67064f2fc5)

In test mode, the whole application lives in a Kubernetes cluster which runs on your computer. Microservices, API
Gateway, databases, Redis, and RabbitMQ now
run as Kubernetes deployments or statefulsets.

The communication between components is done through services (another Kubernetes
component, shown as triangles). In terms of scalability, microservices can have more than one instances, called
replicas. With Kubernetes, we can easily define
a new replica for a microservice if we need more instances to distribute upcoming traffic. For example account service
can have 1 replica and inventory
service can have 3 replicas. Distributing the traffic across the replicas is also done with services.

### Production

![prod](https://github.com/ahmsay/Solidvessel/assets/22731894/988816f4-df39-4ed5-8a3b-d6acd8a69543)

Production environment is logically the same with the test environment. This means that the entire application stack
<b>still runs on a Kubernetes cluster</b>. However, the cluster is on remote servers now, just like a real world
production environment.
I used AWS as a cloud provider for this environment. <b>Please note that I'm not keeping the production environment up
7/24, that would be very costly.</b>
Let's explain each component shown in the diagram one by one:

- **Route 53**: This service is responsible for managing DNS in AWS. When the client types a url, such
  as www.solidvessel.com , it is resolved to the url of the ALB.
  This process is done by Route 53.
- **ALB**: The ingress resource defined for the K8S cluster is converted to an ALB (Application Load Balancer). AWS
  reads the routing rules, services and the SSL configuration we defined in the ingress file and applies all of them in
  the
  ALB which will route the traffic to the cluster. Each ingress defined in K8S has a correspondence in ALB.
- **Target Groups**: These are subcomponents of the ALB. A target group is solely responsible for load balancing. For
  example if a deployment has 3 replicas, it's the target groups responsibility to distribute
  the traffic across the replicas. Each Kubernetes service
  defined in the ingress file is converted to a target group in ALB.
- **Private Subnet**: Giving nodes (servers) of the cluster public IP addresses poses a security risk. Anyone could see
  the nodes and
  try to exploit them. To prevent this, all servers are inside a private network, making them invisible from the outside
  world.
- **NAT Gateway**: Since our nodes don't have a public IP address, they can't directly access to the internet. We need a
  server that does a network address translation (NAT), making our nodes access the internet in a more secure way.
- **Public Subnet**: This is where servers are publicly visible. We need ALB to be public to access our application. We
  also need NAT Gateway to be public to give internet access to our nodes. The security of these two are handled by AWS.
- **Master Nodes**: These are responsible for managing worker nodes. We don't really have any control over master nodes,
  they are fully managed by AWS.
- **Worker Nodes**: These are where our applications run. The Kubernetes cluster drawn in the test environment is
  spanned across
  the worker nodes here.
- **CloudFormation**: The whole infrastructure (nodes, subnets, ALB, NAT gateway etc.) is built on CloudFormation. This
  service
  provides a way to manage AWS resources easier using declarative approaches.

## Topics
- <a href=".docs/run-options.md">Run options<a/>
- <a href=".docs/hexagonal-architecture.md">Hexagonal Architecture<a/>
- <a href=".docs/syncronous-communication.md">Synchronous Communication<a/>
- <a href=".docs/circuit-breaking.md">Circuit Breaking<a/>
- <a href=".docs/asyncronous-communication.md">Asynchronous Communication<a/>
- <a href=".docs/api-gateway.md">API Gateway<a/>
- <a href=".docs/authentication.md">Authentication<a/>
- <a href=".docs/horizontal-scaling.md">Horizontal Scaling<a/>
- <a href=".docs/load-balancing.md">Load Balancing<a/>
- <a href=".docs/traffic-routing.md">Traffic Routing<a/>
- <a href=".docs/shared-module.md">Shared Module<a/>
- <a href=".docs/infrastructure-as-code.md">Infrastructure as Code<a/>
- <a href=".docs/gitops.md">GitOps<a/>
- <a href=".docs/zero-2-prod.md">From Zero to Prod In Minutes<a/>

## Roadmap
- ~~Hexagonal architecture~~
- ~~Domain enrichment~~
- Data enrichment
- Persistence
- Authorization
- Migration to Kafka
- Security improvements
- ~~Building images on CI environment~~
- CQRS
- Monitoring
- Testing
- ~~Exception handling~~
- ~~API gateway~~
