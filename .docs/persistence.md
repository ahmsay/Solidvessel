# Persistence

Currently, all microservices use Postgres as persistent database. It's performance,
efficiency and reliability makes it a valid choice.

In development environment all databases are inside a single Postgres instance. In the test environment all databases
are in separate instances to make the environment more realistic for a microservice architecture. In the production
environment, databases are outside the K8S cluster, and they are provisioned with Amazon RDS. With this approach
production-ready scenarios such as creating a read replica, multi-az environment, taking a snapshot, point in time
recovery are a lot easier.

In these environments the data is not written into any disk, meaning if the database is gone the data will be gone as
well. This makes it easier to setup since they both run locally. In the production environment on the other hand, all
the data is loaded from a previously taken snapshot. This way even if the environment is shut down the data is not lost.