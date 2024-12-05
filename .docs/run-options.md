# Run Options

<b>Note:</b> For all environments, you can use files under the <b>.postman</b> folder to use the application.

## Development

This mode will start Postgres, Redis, RabbitMQ, APISIX and Keycloak as containers in Docker. You can start the microservices with your
IDE.

```shell
docker-compose -f .docker/docker-compose.yaml up -d
```

## Test

This mode will start the whole application in a K8S cluster. You can test it on your own computer if you enable
Kubernetes
on Docker Desktop or Orbstack.

Make sure <a href="https://argo-cd.readthedocs.io/en/stable/getting_started/">ArgoCD<a/> is installed on your cluster.

```shell
kubectl create -f ./.kubernetes/Root.yaml
```

## Production

This mode will start the entire application on AWS. A Kubernetes cluster will be created from scratch (including network
and nodes)
and databases will be created from their previous snapshot.

The provisioning of this environment has a pipeline on AWS that consists 3 stages. All configurations are inside
<a href="../.ci/production">.ci/production<a/> folder.

### 1) Kubernetes Cluster

In the first build stage, <b>create-cluster.yaml</b> and <b>cluster.yaml</b>
files are used to create the K8S cluster. You can change them according to your own needs. Make sure you have necessary
IAM permissions.

### 2) Databases

The second build stage is responsible from creating databases by using Cloudformation. In the <b>databases.yaml</b> file
there are also configurations to handle DNS and firewall rules to establish a connection between worker nodes and
databases.

The databases are created from snapshots, meaning the data is not lost when the databases are down. If this is your
first setup, you must
change the databases inside <b>databases.yaml</b> so they are going to be created from scratch.

### 3) Applications

In the final stage ArgoCD is installed and the rest of the applications (microservices, Redis, RabbitMQ...) are created.
In addition to the applications in the test environment,
tools like External DNS and AWS Load Balancer Controller also created in this environment.

After these stages the production environment is ready to use and microservices are publicly accessible. Tools like
Keycloak and ArgoCD are not publicly accessible
and you should use **kubectl port-forward** for them.