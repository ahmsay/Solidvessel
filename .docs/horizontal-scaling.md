## Horizontal Scaling

We can scale in or out a microservice with `kubectl scale` command.

```bash
kubectl scale --replicas=5 deployment/inventory -n solidvessel
```