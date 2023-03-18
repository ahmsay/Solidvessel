## Circuit Breaking

![circuit_breaking](https://user-images.githubusercontent.com/22731894/226122696-a4b91b5d-8abc-4d2e-88e1-082037b30ab0.svg)

Consider the scenario above. If the inventory microservice is down, this will impact payment microservice as well. So we
need to implement the circuit breaker pattern to make microservices less coupled to each other when doing synchronous
communication. This way, even if the inventory service is down, we can still return the payment information.
