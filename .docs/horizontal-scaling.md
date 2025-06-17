# Horizontal Scaling

Horizontal scaling is used to change the number of instances of an application. To scale a microservice, `kubectl scale`
command can be used.

```bash
kubectl scale --replicas=5 deployment/inventory -n solidvessel
```

In the production environment, a better approach would be to edit the deployment file of the microservice and
push the change to the Git repository. ArgoCD will notify the K8S cluster to apply the change.