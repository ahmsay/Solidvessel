## Traffic Routing

![traffic_routing](https://user-images.githubusercontent.com/22731894/226122916-984ad00d-36a0-40f7-b4ab-6b57789a9e20.svg)

Let's say we want to list all orders in the system. To do this, we need to send a request to the order microservice.
However, the application has one endpoint and four different microservices. So how can we go to the correct microservice
? This is done by ingress (another kubernetes component). We can define routing rules based on url, and ingress routes
the traffic to the related microservice.
