# Microshop
This is an online shopping application designed with microservices architecture.
## Warning
<b>Since the project is having a huge update right now, it is recommended to run it with Docker.</b>
## Useful Scripts
### Run
`docker-compose up`
### Run with Detached Mode
`docker-compose up -d`<br>
If you use detached mode, containers will be automatically removed after you stop the application.
### Stop
`docker-compose down`
### Remove All Containers
`docker rm $(docker ps -aq)`
### Push Images
`docker push <hub-user>/<repo-name>:<tag>`
## Project Details
My main goal is to learn about microservices, so I kept the domain part of the project as simple as possible. The project is about recording transactions of a shopping application. The relations about entities are: One customer can have multiple payments and orders, each order is related to a payment and one payment can have multiple products.
### Client Services
There are four client services: account service, inventory service, payment service and order service. Each of them has their own database (PostgreSQL). If you'll run the application locally, you must create clients' databases and a database user. You don't need to create tables. On the other hand, with Docker Compose, databases and user will be created automatically.
### Spring Data
If you look into any client service, you won't see a repository implementation. There are only interfaces. This is the work of Spring Data which has a bunch of utilities and makes CRUD operations a lot easier. Spring Data CRUD repositories don't need to be implemented. When you write your method with a specific format, it is automatically resolved.
### RabbitMQ
When a new payment is saved, payment id should be passed to related products. But products are not in the same database with payments. There can be a rest call to inventory service to update related products but payment service doesn't need to know about inventory service. Furthermore, this approach doesn't guarantee to keep databases synchronized since inventory service might be down, and the request would be lost.</br>

So I decided to use event driven approach with RabbitMQ to solve this problem. In this approach, microservices send events when an action happens. When a new payment is saved, an event will be sent to inventory service through a message broker. If inventory service is down, the event won't be lost. It can be processed later when the service is up again.
### Communication and Service Discovery
When a service needs to get information which is stored in another service, it sends an HTTP request to that service. Therefore, the url of the service must be known but url depends on where the application is. It can be in a local machine, or a container, or deployed in a server. So how do we know the correct url to send our request? Actually, we don't need to. This is where service discovery comes in.</br>

In this project, all other services register to the discovery server as discovery clients and each of them has their own name located in their <b>bootstrap.yml</b> file. So instead of writing the url to send the request, we write the service name. This encapsulates the varying service url. The discovery server knows the url, and the rest is just REST. It also does load balancing which is crucial when you'll have multiple instances. There are server side and client side service discoveries. I used Eureka, which makes client side discovery. We can also see registered services and their status on discovery server. I registered the config server as well just to see its status. Also, if the discovery server goes down, other instances will continue to communicate each other by using cache.
### Configuration Server
Spring Framework provides some options to separate our configuration from code, which makes our application to run in different environments without touching the code but Spring Cloud Configuration Server takes it to another level. We can update our configuration properties without restarting the application.</br>

To do this, first I created a separate service and declared it as configuration server. Then I declared the four client services as configuration clients. Spring Cloud Config provides a <b>Git</b> repository to store common properties and update them dynamically. So I created a <a href="https://github.com/ahmsay/Microshop-Configuration" target="_blank">Git repo</a> and gave its url to the config server. Now client services fetch data from the config server while starting.</br>

So how do we update our config properties without restarting the application? Once we change a property in the Git repo, config server automatically fetches it but client services will still get the old value. That's because it's not their job to continuously ask the config server "Hey, are you updated?". To trigger them, we send a <b>refresh</b> request to the intended REST controller. Now we get the updated property in run time.
### Docker
Docker simply lets you run an application without relying on dependencies. All services have a Dockerfile to create their images. Updating the jar and creating an image for 6 services was a-bit time-consuming, so I wrote a <a href="https://github.com/ahmsay/Microshop/blob/master/extra/build_images.sh" target="_blank">shell script</a> to do it with one command.
### Docker Hub
Once images are created, I push them to my docker repository with `docker push <hub-user>/<repo-name>:<tag>` command. Since `docker-compose push` doesn't work (god knows why) I push them separately.
### Docker Compose
If you run images of the services separately, Docker will put them in separated networks, and they won't be able to communicate each other but if you run them with Docker Compose, they will be in the same network. So I created a docker-compose.yml file. If images don't exist in a local device, they will be pulled but there is still a problem. Since the services start in a different network, they are not running on localhost anymore. I did port mapping, but it is only for me to access them from the host. Client services need to get the url of configuration server, discovery server and their databases' urls to run properly.</br>

To solve this problem, I wrote an additional command in the <b>docker-compose.yml</b> file for client services to run them with a different Spring profile. For example when I try to run the application with Docker Compose, account service starts with a new Spring profile. Now I can override the discovery server url for that profile. The new url is the same service name in the docker-compose.yml file: <b>discovery-server</b>. Spring is clever enough to resolve it. Same thing also applies for config server and database. Now with the overridden urls, account service and other services can connect to the discovery server, the config server and their databases. Furthermore, -thanks to Spring profiles- I don't need to change my code to run the application in different environments.</br>

So is it done now? NO! Client services first try to connect the config server, then the discovery server. When using docker compose, all services start at the same time. That means the config server may not be ready when a client tries to connect. That causes the client to fail to start. To fix it, I added <b>spring-retry</b> dependency to client services. This solution was not working with <b>application.yml</b> files, so I started to use <b>bootstrap.yml</b> files (Bootstrap context loads before the application context). Now if config server is not ready, clients retry connecting instead of shutting down. Once they connect to the config server, they'll try to connect the discovery server. Fortunately, if the discovery server is not ready, they won't shut down and automatically try to reconnect.</br>

In conclusion, to run this application with Docker Compose, you only need the <b>docker-compose.yml</b> file. When you write `docker-compose up` command, after a bunch of errors (services desperately trying to connect each other) it will run.
### Testing
Currently, there is no unit test or integration test but there are <a href="https://github.com/ahmsay/Microshop/blob/master/extra/microshop.postman_collection.json" target="_blank">API tests</a>. You can import it in Postman to test the application.
