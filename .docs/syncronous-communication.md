## Synchronous Communication

![image](https://user-images.githubusercontent.com/22731894/159354677-8fb0a2fd-2a42-464a-9b09-6b76d630dfd5.svg)

Let's say we want to retrieve all products that are related to a payment, along with the payment information. Payment
information is in Payment DB and product information is in Inventory DB. To retrieve product information, we need to
send and HTTP request to the inventory microservice. The service (kubernetes component) for inventory microservice act
as a bridge to achieve this goal.
