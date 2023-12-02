# Asynchronous Communication

![asynchronous_communication](https://user-images.githubusercontent.com/22731894/226122673-ba6ad927-3413-4e5f-b1fe-e1415930c7fc.svg)

If a payment is created, an order related to the payment must be created as well. Since these two tasks are not directly
connected to each other, we can to this asynchronously. This approach is also another way to provide loose coupling
between microservices. Even if the order microservice is down, the order can be created when it's up again.
