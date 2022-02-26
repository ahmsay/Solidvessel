kubectl create ns microshop

cd rabbitmq
kubectl create -f pod.yaml
kubectl create -f service.yaml
cd ..

cd redis
kubectl create -f pod.yaml
kubectl create -f service.yaml
cd ..

cd account/db
kubectl create -f pod.yaml
kubectl create -f service.yaml
cd ../..

cd inventory/db
kubectl create -f pod.yaml
kubectl create -f service.yaml
cd ../..

cd order/db
kubectl create -f pod.yaml
kubectl create -f service.yaml
cd ../..

cd payment/db
kubectl create -f pod.yaml
kubectl create -f service.yaml
cd ../..
