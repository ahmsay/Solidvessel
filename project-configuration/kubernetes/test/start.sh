kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.1.1/deploy/static/provider/cloud/deploy.yaml

kubectl create ns microshop

cd rabbitmq
kubectl create -f pod.yaml
kubectl create -f service.yaml
cd ..

cd redis
kubectl create -f pod.yaml
kubectl create -f service.yaml
cd ..

cd auth/db
kubectl create -f pod.yaml
kubectl create -f service.yaml
cd ../app
kubectl create -f deployment.yaml
kubectl create -f service.yaml
cd ../..

cd account/db
kubectl create -f pod.yaml
kubectl create -f service.yaml
cd ../app
kubectl create -f deployment.yaml
kubectl create -f service.yaml
cd ../..

cd inventory/db
kubectl create -f pod.yaml
kubectl create -f service.yaml
cd ../app
kubectl create -f deployment.yaml
kubectl create -f service.yaml
cd ../..

cd order/db
kubectl create -f pod.yaml
kubectl create -f service.yaml
cd ../app
kubectl create -f deployment.yaml
kubectl create -f service.yaml
cd ../..

cd payment/db
kubectl create -f pod.yaml
kubectl create -f service.yaml
cd ../app
kubectl create -f deployment.yaml
kubectl create -f service.yaml
cd ../..

cd ingress
kubectl create -f ingress-resource.yaml # the ingress controller may not be ready. try to run this line again if you get an error.
cd ..
