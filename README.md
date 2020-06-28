# Online-Shopping-Application
This is an online shopping application designed with microservices architecture.<br>
Docker Repository: https://hub.docker.com/repository/docker/ahmsay/online-shopping-application

## To Run
### Option 1 (Use the images directly)
#### Prerequisites
- docker
- docker-compose
#### Steps
1. Pull the images with `docker pull --all-tags ahmsay/online-shopping-application` command.
2. Use the <b>docker-compose.yml</b> file to start the application.

### Option 2 (Build the images yourself)
#### Prerequisites
- java 11
- maven
- docker
- docker-compose
#### Steps
1. Clone this repository.
2. Go to the directory of each service and run `mvn package` command to build the jar files.
3. Run `docker-compose up` command from the main directory.

### Option 3 (Run without docker)
#### Prerequisites
- java 11
- maven
#### Steps
1. Clone this repository.
2. Use your favorite IDE.

## Test It With Postman
You can import <b>online-shopping-app.postman_collection.json</b> file in Postman to test the application.
