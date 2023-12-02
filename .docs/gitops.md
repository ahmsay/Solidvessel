# GitOps

The aim of GitOps is to manage your configuration using a Git repository. In the production environment
there are a lot of Kubernetes resources. Managing them with kubectl or Kubernetes Dashboard doesn't guarantee that
our resources is the same with the K8S manifest files we created. For example, we defined the payment service has
2 replicas in the deployment.yaml file but in the actual cluster it might have 3 replicas. To prevent misconfigurations,
we need to control that our actual configuration and K8S files is the same.

Thankfully, ArgoCD solves this problem for us. ArgoCD is a GitOps tool that acts as a bridge between our Git repo and the
K8S cluster. When we need to apply a change to the cluster, we apply it on our configuration files and push them to the Git
repo. After that, ArgoCD detects the change and triggers the K8S cluster to apply that change. This way, we make sure that
our K8S resources is the same with the configuration files we defined.