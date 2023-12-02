# Load Balancing

![load_balancing](https://user-images.githubusercontent.com/22731894/226122887-2722726e-ba25-4e37-975f-62ca493ee45f.svg)

The reason we cannot send a request directly to a microservice is that the microservice may have multiple instances and
each of them will have different endpoints. This is where service (that little triangle) comes in. Service is a
kubernetes component and provides an endpoint to a consumer. It also acts as a load balancer. In the image below, the
request will be received by an appropriate payment microservice.
