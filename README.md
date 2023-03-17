# Solidvessel

This is a shopping application designed with microservices architecture. My main goal is to learn about
microservices, so I kept the domain part as simple as possible. The project is about recording transactions of a shopping application.
<a href=".docs/run-options.md">Check here to see how to run.<a/>

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
![prod](https://user-images.githubusercontent.com/22731894/225763807-d85118d5-c3aa-4a37-b3b9-9ce144879e68.svg)

Production environment is logically the same with the test environment. This means that the entire application stack 
<b>still runs on a Kubernetes cluster</b>. However, the cluster is on remote servers now, just like a real world production environment. 
I used AWS as a cloud provider for this environment. <b>Please note that I'm not keeping the production environment up 7/24, that would be very costly.</b> 
Let's explain each component shown in the diagram one by one:
- ALB: The ingress resource we defined for the K8S cluster is converted to an ALB (Application Load Balancer). AWS reads 
the routing rules, services and the SSL configuration we defined in the ingress file and applies all of them in the ALB.
Each ingress defined in K8S has a correspondence in ALB.
- Target Groups: These are subcomponents of the ALB. A target group is solely responsible for load balancing. For example if
the inventory service has 3 replicas, it's the target group of the inventory service's responsibility to distribute the traffic across the replicas. Each Kubernetes service
defined in the ingress file is converted to a target group on ALB.
- Route 53: This service is responsible for managing DNS in AWS. Each microservice has a DNS record, and they are forwarded through the ALB.
When the client types a url, such as inventory.solidvessel.com , it should be resolved to the url of the ALB.
This process is done by Route 53.
- Private Subnet: Giving nodes (servers) of the cluster public IP addresses poses a security risk. Anyone could see the nodes and
try to exploit them. To prevent this, all servers are inside a private network, making them invisible from the outside world.
- Public Subnet: This is where servers are publicly visible. We need ALB to be public to access our application. We also need
NAT Gateway to be public to give internet access to our nodes. The security of these two are handled by AWS.
- NAT Gateway: Since our nodes don't have a public IP address, they can't directly access to the internet. We need a server
that does a network address translation (NAT), making our nodes to access the internet in a more secure way.
- Master Nodes: These are responsible for managing worker nodes. We don't really have any control over master nodes, 
they are fully managed by AWS.
- Worker Nodes: These are where our applications run. The Kubernetes cluster drawn in the test environment is spanned across
the worker nodes here.
- CloudFormation: The whole infrastructure (nodes, subnets, ALB, NAT gateway etc.) is built on CloudFormation. This service
provides a way to manage AWS resources easier using declarative approaches.

## Topics
- <a href=".docs/run-options.md">Run options<a/>
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

## Roadmap
~~- Hexagonal architecture~~
- Domain enrichment
- Data enrichment
- Persistence
- Authorization
- Migration to Kafka
- Security improvements
- Building images on CI environment
- CQRS
