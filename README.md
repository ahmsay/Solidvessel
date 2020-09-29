# Online-Shopping-Application
This is an online shopping application designed with microservices architecture.<br>
Docker Repository: https://hub.docker.com/repository/docker/ahmsay/online-shopping-application

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
My main goal is to learn about microservices, so I kept the domain part of the project as simple as possible. The project is about recording transactions of a shopping application. The relations about entities are: One customer can have multiple payments and orders, each order has a payment and one payment can have multiple products.
### Client Services
There are four client services: account service, inventory service, payment service and order service. Each of them has their own database (Currenlty H2).
### Communication and Service Discovery

### Configuration

### Docker

### Docker Compose

### Docker Hub

### Spring Data
