## Circuit Breaking

![image](https://user-images.githubusercontent.com/22731894/159343282-9e3c4b44-8212-4c44-a79f-49336baaf9c6.svg)

Consider the scenario above. If the inventory microservice is down, this will impact payment microservice as well. So we
need to implement the circuit breaker pattern to make microservices less coupled to each other when doing synchronous
communication. This way, even if the inventory service is down, we can still return the payment information.
