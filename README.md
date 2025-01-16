# Solidvessel

[![codecov](https://codecov.io/gh/ahmsay/Solidvessel/graph/badge.svg?token=KVJ8AABE5Z)](https://codecov.io/gh/ahmsay/Solidvessel)
![](https://codebuild.eu-central-1.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiUFlTcHRnNXlIN0tVMm5veE9XU1VZblp2SEwzQ3Q3R3ZmQ3N2enI3cWtENEVZbmtleVFBOFJ2OWpVNy9NSEVrMjhHU0lqODY0U0NiNWh3L0M0VUREMG5NPSIsIml2UGFyYW1ldGVyU3BlYyI6Imw3SHZtNkErV0NPckpWUkQiLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=master)

This is a shopping application designed with microservices architecture. My main goal is to learn about
devops and microservices, but I am also improving the domain part as much as possible. The project is about recording
transactions of a shopping application.
<a href=".docs/run-options.md">Check to see how to run.<a/>

## Architecture
### Development
![development](https://github.com/ahmsay/Solidvessel/assets/22731894/6b70af30-2090-4b8e-bd10-dbd3d4058e11)

In development mode, microservices are running as separate processes on your computer. The API Gateway, database,
RabbitMQ, Redis and Keycloak run
as Docker containers. Since all microservices run on the same device, they should expose a different port. The
communication between client
and microservices is done through the API Gateway. Databases are in the same instance for the sake of simplicity in this
environment.

### Test

![test](https://github.com/ahmsay/Solidvessel/assets/22731894/f0a32cb2-6228-44cd-91c0-8e9d9243d0df)

In test mode, the whole application lives in a Kubernetes cluster which runs on your computer. Microservices, API
Gateway, databases, Redis, RabbitMQ and Keycloak now
run as Kubernetes deployments or statefulsets.

The communication between components is done through services (another Kubernetes
component, shown as triangles). In terms of scalability, microservices can have more than one instances, called
replicas. With Kubernetes, we can easily define
a new replica for a microservice if we need more instances to distribute upcoming traffic. For example account service
can have 1 replica and inventory
service can have 3 replicas. Distributing the traffic across the replicas is also done with services.

### Production

![prod](https://github.com/ahmsay/Solidvessel/assets/22731894/2ee355bc-5f97-4781-b86f-4d16b77f3831)

Production environment is very similar to the test environment. However, the cluster is on remote servers now, just like
a real world
production environment.
I used AWS as a cloud provider for this environment. I also provisioned the databases on AWS, not inside Kubernetes. <b>
Please note that I'm not keeping the production environment up
7/24, that would be very costly.</b>
Let's explain each component shown in the diagram one by one:

- **Route 53**: This service is responsible for managing DNS in AWS. When the client types a url, such
  as www.solidvessel.com , it is resolved to the url of the ALB.
  This process is done by Route 53.
- **ALB**: The ingress resource defined for the K8S cluster is converted to an ALB (Application Load Balancer). AWS
  reads the routing rules, services and the SSL configuration we defined in the ingress file and applies all of them in
  the ALB which will route the traffic to the cluster. Each ingress defined in K8S has a correspondence in ALB.
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
- **RDS**: All databases are provisioned by using RDS (Relational Database Service). The engine is still PostgreSQL but
  backups,
  monitoring, high availability and disaster recovery is much easier. When the environment is going up, the databases
  are
  provisioned from previous snapshots, so the data is never lost.

## Topics
- <a href=".docs/run-options.md">Run options<a/>
- <a href=".docs/hexagonal-architecture.md">Hexagonal Architecture<a/>
- <a href=".docs/syncronous-communication.md">Synchronous Communication<a/>
- <a href=".docs/circuit-breaking.md">Circuit Breaking<a/>
- <a href=".docs/asyncronous-communication.md">Asynchronous Communication<a/>
- <a href=".docs/api-gateway.md">API Gateway<a/>
- <a href=".docs/authentication-authorization.md">Authentication & Authorization<a/>
- <a href=".docs/horizontal-scaling.md">Horizontal Scaling<a/>
- <a href=".docs/load-balancing.md">Load Balancing<a/>
- <a href=".docs/entity-relations.md">Entity Relations<a/>
- <a href=".docs/caching.md">Caching<a/>
- <a href=".docs/shared-module.md">Shared Module<a/>
- <a href=".docs/infrastructure-as-code.md">Infrastructure as Code<a/>
- <a href=".docs/gitops.md">GitOps<a/>
- <a href=".docs/zero-2-prod.md">From Zero to Prod In Minutes<a/>
- <a href=".docs/testing.md">Testing<a/>

## Roadmap

- Domain enrichment (add more features)
- Data enrichment (add millions of records for prod environment)
- Monitoring (Prometheus, Grafana etc)
- Maybe it's time to create a frontend ??
- Apply CQRS in one of the microservices
- Migration from RabbitMQ to Kafka
- Microservice with different framework
- Microservice with different programming language
