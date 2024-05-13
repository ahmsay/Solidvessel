# Caching

![api_gateway](https://github.com/ahmsay/Solidvessel/assets/22731894/ca806cf8-c7ea-466b-8cd5-3b34efe3352a)

Retrieving data from the database all the time might be costly in a large scale application.
Caching is a concept to mitigate this cost. The main idea is to retrieve the data from the cache
if it's available. If not, going to database is the second and mandatory option. This approach is great for
data that is retrieved frequently.

In this project, I used Redis for caching and implemented a caching mechanism wherever possible. If the data isn't
stored
in Redis, it will be put in Redis with its unique key after it's fetched from the database. That way, if it's requested
again,
it can be retrieved directly from Redis.

The caching is not only for db level operations, it works with REST operations as well. Therefore, microservices don't
always
need to fetch a data from another microservice, reducing the coupling even more.

Although implementation of a caching mechanism seem easy if you only look at the code, determining the strategy is quite
difficult.
What is worth caching? Will there be any side effects? Is it ok to retrieve outdated information? When are you going to
evict the cache?
Answers of these questions depend on your business needs and infrastructure while applying a caching mechanism.
