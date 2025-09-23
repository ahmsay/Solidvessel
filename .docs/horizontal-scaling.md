# Horizontal Scaling

Horizontal scaling is used to change the number of instances of an application. To scale a microservice in a K8S
cluster, normally `kubectl scale`
command could be used. But since both test and the production environment applies the GitOps approach, a better solution
would be to edit the deployment file of the microservice and
push the change to the Git repository. Then ArgoCD will notify the K8S cluster to apply the change.