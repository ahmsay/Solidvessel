# Circuit Breaking

![circuit_breaking](https://github.com/ahmsay/Solidvessel/assets/22731894/681dbd73-7042-454e-ae6f-de289ebb6d9c)

Consider the scenario above. If the inventory microservice is down, this will impact payment microservice as well. So we
need to implement the circuit breaker pattern to make microservices less coupled to each other when doing synchronous
communication. This way, even if the inventory service is down, we can still return the cart information and for the products
we can return a default response such as retrieving them from Redis cache.