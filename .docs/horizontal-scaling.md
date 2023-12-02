# Horizontal Scaling

We can scale in or out a microservice with `kubectl scale` command.

```bash
kubectl scale --replicas=5 deployment/inventory -n solidvessel
```

In the production environment, a better approach would be to edit the deployment file of the inventory microservice and
push the change to the Git repository. ArgoCD will notify the K8S cluster to apply the change.