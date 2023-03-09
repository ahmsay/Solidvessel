## Traffic Routing

![image](https://user-images.githubusercontent.com/22731894/159344327-169d07d4-ae79-4b2a-9ed5-2b012bec87cf.svg)

Let's say we want to list all orders in the system. To do this, we need to send a request to the order microservice.
However, the application has one endpoint and four different microservices. So how can we go to the correct microservice
? This is done by ingress (another kubernetes component). We can define routing rules based on url, and ingress routes
the traffic to the related microservice.
