kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.4.0/deploy/static/provider/cloud/deploy.yaml
kubectl create ns microshop
kubectl apply -f ./run/test -R
# ingress resource cannot be created at the first attempt since other ingress resources are not ready yet. you can rerun it with this:
# kubectl apply -f ./run/test/ingress/ingress-resource.yaml
