# Synchronous Communication

![synchronous_communication](https://user-images.githubusercontent.com/22731894/226122615-a35e5790-031b-4c93-ac6f-d1441350f1cb.svg)

Let's say we want to retrieve all products inside the cart, along with the cart information. Cart
information is in payment db and product information is in inventory db. To retrieve product information, we need to
send and HTTP request to the inventory microservice. The service (kubernetes component) for inventory microservice acts
as a bridge to achieve this goal. For development environment, this is more straightforward and payment microservice sends
the request directly to the inventory microservice.