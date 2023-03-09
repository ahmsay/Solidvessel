## Load Balancing

![image](https://user-images.githubusercontent.com/22731894/159343897-28494cdb-a70c-4595-83ca-8964a97f5525.svg)

The reason we cannot send a request directly to a microservice is that the microservice may have multiple instances and
each of them will have different endpoints. This is where service (that little triangle) comes in. Service is a
kubernetes component and provides an endpoint to a consumer. It also acts as a load balancer. In the image below, the
request will be received by an appropriate payment microservice.
