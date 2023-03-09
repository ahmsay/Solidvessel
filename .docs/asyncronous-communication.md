## Asynchronous Communication

![image](https://user-images.githubusercontent.com/22731894/159354544-46ae0621-cb0a-41f1-b78f-e09f1296db8a.svg)

If a payment is created, an order related to the payment must be created as well. Since these two tasks are not directly
connected to each other, we can to this asynchronously. This approach is also another way to provide loose coupling
between microservices. Even if the order microservice is down, the order can be created when it's up again.
