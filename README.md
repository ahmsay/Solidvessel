# THIS README IS NOT FINISHED

# Microshop

This is an online shopping application designed with microservices architecture. My main goal is to learn about
microservices, so I kept the domain part as simple as possible. The project is about recording transactions of a
shopping application.

## Architecture

<img src="https://user-images.githubusercontent.com/22731894/154577454-7bc6e153-b6c9-4561-a3c3-4e70bb4a9d63.png" alt="diagram">

## Run

```bash
sh ./project-configuration/kubernetes/test/start.sh
```

### Run Options

#### Development

This mode will start the databases and the message queue in the K8S cluster. You can start the microservices with your
IDE.

`sh ./project-configuration/kubernetes/dev/start.sh`

#### Test

This mode will start the whole application in K8S cluster.

`sh ./project-configuration/kubernetes/test/start.sh`

#### Production (will be added)

This mode will start the whole application in K8S cluster in a more secure way.<br>
` `

## Capabilities

- Read / Write operations
- Synchronous and asynchronous communication
- Horizontal scaling
- Load balancing
- Traffic routing
- Authentication (will be added)
- Circuit breaking (will be added)

## Entity Relationships

## Synchronous Communication

## Asynchronous Communication

## Horizontal Scaling

## Load Balancing

## Traffic Routing

## Testing

You can test the application by using
<a href="https://github.com/ahmsay/Microshop/blob/master/microshop.postman_collection.json" target="_blank">
API tests</a> that can be imported into Postman.