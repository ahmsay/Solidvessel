## Synchronous Communication

![synchronous_communication](https://user-images.githubusercontent.com/22731894/226122615-a35e5790-031b-4c93-ac6f-d1441350f1cb.svg)

Let's say we want to retrieve all products that are related to a payment, along with the payment information. Payment
information is in Payment DB and product information is in Inventory DB. To retrieve product information, we need to
send and HTTP request to the inventory microservice. The service (kubernetes component) for inventory microservice act
as a bridge to achieve this goal.
