# Hexagonal Architecture

![hexagonal](https://github.com/ahmsay/Solidvessel/assets/22731894/ebeb6423-519e-4338-bdaf-c56de36045c4)

Hexagonal architecture aims to separate the domain and infra part of the application. This makes your
domain independent of the framework you use and your framework easier to change. In the diagram above, the blue
part is infra and red part is the domain. The interaction between domain and infra is done through interfaces,
which makes them loosely coupled.

The class names can vary between architectures, also it's not required to have only 2 layers. Some architectures
have 3 layers such as domain, application and infra. The main goal is still to keep the domain and infra part
isolated from each other.