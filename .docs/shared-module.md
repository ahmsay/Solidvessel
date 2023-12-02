# Shared Module

Microservices can share a module with common dependencies instead of re-writing same dependencies in all of them. This
might oppose the idea of microservices being fully independent, but it's also useful when you are sure that the shared
code will always be same for all microservices. In this project, extracting the session info functionality and
authentication token classes are in the shared module.
