

# Solidvessel

[![codecov](https://codecov.io/gh/ahmsay/Solidvessel/graph/badge.svg?token=KVJ8AABE5Z)](https://codecov.io/gh/ahmsay/Solidvessel)
![](https://codebuild.eu-central-1.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiUFlTcHRnNXlIN0tVMm5veE9XU1VZblp2SEwzQ3Q3R3ZmQ3N2enI3cWtENEVZbmtleVFBOFJ2OWpVNy9NSEVrMjhHU0lqODY0U0NiNWh3L0M0VUREMG5NPSIsIml2UGFyYW1ldGVyU3BlYyI6Imw3SHZtNkErV0NPckpWUkQiLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=master)

Esta es una aplicación de compras diseñada con una arquitectura de microservicios. Mi objetivo principal es aprender sobre DevOps y microservicios, pero también estoy mejorando la parte del dominio lo más posible. El proyecto trata sobre el registro de transacciones de una aplicación de compras.
<a href=".docs/run-options.md">Consulta cómo ejecutarlo.<a/>

## Arquitectura
### Desarrollo
![development](https://github.com/ahmsay/Solidvessel/assets/22731894/6b70af30-2090-4b8e-bd10-dbd3d4058e11)

En el entorno de desarrollo, los microservicios se ejecutan como procesos separados en tu computadora. El API Gateway, la base de datos, RabbitMQ, Redis y Keycloak se ejecutan como contenedores Docker. Dado que todos los microservicios se ejecutan en el mismo dispositivo, deben exponer un puerto diferente. La comunicación entre el cliente y los microservicios se realiza a través del API Gateway. Las bases de datos están en la misma instancia por motivos de simplicidad en este entorno.

### Pruebas

![test](https://github.com/ahmsay/Solidvessel/assets/22731894/f0a32cb2-6228-44cd-91c0-8e9d9243d0df)

En el entorno de pruebas, toda la aplicación reside en un clúster de Kubernetes que se ejecuta en tu computadora. Los microservicios, el API Gateway, las bases de datos, Redis, RabbitMQ y Keycloak ahora se ejecutan como deployments o statefulsets de Kubernetes.

La comunicación entre los componentes se realiza a través de servicios (otro componente de Kubernetes, representado como triángulos). En términos de escalabilidad, los microservicios pueden tener más de una instancia, llamadas réplicas. Con Kubernetes, podemos definir fácilmente una nueva réplica para un microservicio si necesitamos más instancias para distribuir el tráfico entrante. Por ejemplo, el servicio de cuentas puede tener 1 réplica y el servicio de inventario puede tener 3 réplicas. La distribución del tráfico entre las réplicas también se realiza con servicios.

### Producción

![prod](https://github.com/ahmsay/Solidvessel/assets/22731894/2ee355bc-5f97-4781-b86f-4d16b77f3831)

El entorno de producción es muy similar al entorno de pruebas. Sin embargo, el clúster ahora está en servidores remotos, al igual que en un entorno de producción del mundo real. Utilicé AWS como proveedor de nube para este entorno. También aprovisioné las bases de datos en AWS, no dentro de Kubernetes. <b>Ten en cuenta que no mantengo el entorno de producción activo 24/7, ya que eso sería muy costoso.</b>
Exploremos cada componente que se muestra en el diagrama uno por uno:

- **Route 53**: Este servicio es responsable de gestionar el DNS en AWS. Cuando el usuario escribe una URL, como www.solidvessel.com, se resuelve a la URL del ALB. Este proceso lo realiza Route 53.
- **ALB**: El recurso de ingress definido para el clúster de K8S se convierte en un ALB (Application Load Balancer). AWS lee las reglas de enrutamiento, los servicios y la configuración SSL que definimos en el archivo de ingress y los aplica todos en el ALB, que enrutará el tráfico al clúster. Cada ingress definido en K8S tiene una correspondencia en el ALB.
- **Target Groups**: Son subcomponentes del ALB. Un grupo de destino se encarga exclusivamente del balanceo de carga. Por ejemplo, si un deployment tiene 3 réplicas, es responsabilidad del grupo de destino distribuir el tráfico entre las réplicas. Cada servicio de Kubernetes definido en el archivo de ingress se convierte en un grupo de destino en el ALB.
- **Private Subnet**: Asignar direcciones IP públicas a los nodos (servidores) del clúster representa un riesgo de seguridad. Cualquier persona podría ver los nodos e intentar explotarlos. Para prevenir esto, todos los servidores están dentro de una red privada, haciéndolos invisibles desde el exterior.
- **NAT Gateway**: Dado que nuestros nodos no tienen una dirección IP pública, no pueden acceder directamente a Internet. Necesitamos un servidor que realice una traducción de dirección de red (NAT), permitiendo que nuestros nodos accedan a Internet de manera más segura.
- **Public Subnet**: Aquí es donde los servidores son visibles públicamente. Necesitamos que el ALB sea público para acceder a nuestra aplicación. También necesitamos que el NAT Gateway sea público para otorgar acceso a Internet a nuestros nodos. La seguridad de estos dos componentes es gestionada por AWS.
- **Master Nodes**: Estos son responsables de gestionar los nodos trabajadores. Realmente no tenemos control sobre los nodos maestros, son gestionados completamente por AWS.
- **Worker Nodes**: Aquí es donde se ejecutan nuestras aplicaciones. El clúster de Kubernetes dibujado en el entorno de pruebas se distribuye a través de los nodos trabajadores aquí.
- **CloudFormation**: Toda la infraestructura (nodos, subredes, ALB, NAT Gateway, etc.) se construye sobre CloudFormation. Este servicio proporciona una manera de gestionar los recursos de AWS de forma más sencilla utilizando enfoques declarativos.
- **RDS**: Todas las bases de datos se aprovisionan mediante RDS (Relational Database Service). El motor sigue siendo PostgreSQL, pero las copias de seguridad, la supervisión, la alta disponibilidad y la recuperación ante desastres son mucho más fáciles. Cuando se inicia el entorno, las bases de datos se aprovisionan a partir de instantáneas anteriores, por lo que los datos nunca se pierden.

## Temas
- <a href=".docs/run-options.md">Opciones de ejecución<a/>
- <a href=".docs/hexagonal-architecture.md">Arquitectura Hexagonal<a/>
- <a href=".docs/syncronous-communication.md">Comunicación Sincrónica<a/>
- <a href=".docs/circuit-breaking.md">Circuit Breaking<a/>
- <a href=".docs/asyncronous-communication.md">Comunicación Asincrónica<a/>
- <a href=".docs/api-gateway.md">API Gateway<a/>
- <a href=".docs/authentication-authorization.md">Autenticación y Autorización<a/>
- <a href=".docs/horizontal-scaling.md">Escalado Horizontal<a/>
- <a href=".docs/load-balancing.md">Balanceo de Carga<a/>
- <a href=".docs/entity-relations.md">Relaciones de Entidades<a/>
- <a href=".docs/caching.md">Caché<a/>
- <a href=".docs/shared-module.md">Módulo Compartido<a/>
- <a href=".docs/infrastructure-as-code.md">Infraestructura como Código<a/>
- <a href=".docs/gitops.md">GitOps<a/>
- <a href=".docs/zero-2-prod.md">De Cero a Producción en Minutos<a/>
- <a href=".docs/testing.md">Pruebas<a/>

## Hoja de ruta

- Enriquecimiento del dominio (agregar más funciones)
- Enriquecimiento de datos (agregar millones de registros para el entorno de producción)
- Monitorización (Stack LGTM)
- ~~Asistente de IA (agente para explicar la documentación)~~
- Quizás sea hora de crear un frontend ¿¿
- Aplicar CQRS en uno de los microservicios
- Migración de RabbitMQ a Kafka
- Microservicio con un framework diferente
- Microservicio con un lenguaje de programación diferente
