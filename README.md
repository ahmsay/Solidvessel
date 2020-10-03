# Online-Shopping-Application
This is an online shopping application designed with microservices architecture.<br>
Docker Repository: https://hub.docker.com/r/ahmsay/online-shopping-application
## To Run
### Option 1 (Run with docker)
#### Prerequisites
- docker
- docker-compose
#### Steps
1. Use the <b>docker-compose.yml</b> file to start the application with `docker-compose up` command.
### Option 2 (Run without docker)
#### Prerequisites
- java 11
- maven
#### Steps
1. Clone this repository.
2. Use your favorite IDE.
## Test It With Postman
You can import <b>extra/online-shopping-app.postman_collection.json</b> file in Postman to test the application.
## Project Details
My main goal is to learn about microservices, so I kept the domain part of the project as simple as possible. The project is about recording transactions of a shopping application. The relations about entities are: One customer can have multiple payments and orders, each order is related to a payment and one payment can have multiple products.
### Client Services
There are four client services: account service, inventory service, payment service and order service. Each of them has their own database (Currenlty <b>H2</b>, an in-memory database).
### Spring Data
If you look into any client service, you won't see a repository implementation. There are only interfaces. This is the work of Spring Data which has bunch of utilities and makes CRUD operations a lot easier. Spring Data CRUD repositories don't need to be implemented. When you write your method with a specific format, it is automatically resolved.
### Communication and Service Discovery
When a service needs to get information which is stored in another service, it sends an HTTP request to that service. Therefore, the url of the service must be known. But url depends on where the application is. It can be in a local machine, or a container, or deployed in a server. So how do we know the correct url to send our request? Actually, we don't need to. This is where service discovery comes in.</br>

In this project, all other services register to the discovery server as discovery clients and each of them has their own name located in their <b>bootstrap.yml</b> file. So instead of writing the url to send the request, we write the service name. This encapsulates the varying service url. The discovery server knows the url and the rest is just REST. It also does load balancing which is crucial when you'll have multiple instances. There are server side and client side service disvoveries. I used Eureka, which makes client side discovery. We can also see registered services and their status on discovery server. Also if the discovery server goes down, other instances will continue to communicate each other by using cache.
### Configuration Server
Spring Framework provides some options to seperate our configuration from code, which makes our application to run in different environments without touching the code. But Spring Cloud Configuration Server takes it to another level. We can update our configuration properties without restarting the application.</br>

To do this, first I created a seperate service and declared it as configuration server. Then I declared the four client services as configuration clients. Spring Cloud Config provides a <b>git</b> repository to store common properties and update them dynamically. So I created a <a href="https://github.com/ahmsay/Online-Shopping-Application-Configuration">git repo</a> and gave its url to the config server. Now client services fetch data from the config server while starting.</br>

So how do we update our config properties without restarting the application? Once we change a property in the git repo, config server automatically fetches it. But client services will still get the old value. That's because it's not their job to continuously ask the config server "Hey, are you updated?". To trigger them, we send a <b>refresh</b> request to the intended REST controller. Now we get the updated property in run time.
### Docker

### Docker Compose

### Docker Hub
