kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.4.0/deploy/static/provider/cloud/deploy.yaml
kubectl create ns microshop
kubectl apply -f ./kubernetes/test -R
