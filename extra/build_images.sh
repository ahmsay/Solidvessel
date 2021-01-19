cd ..

cd account-service
mvn clean package
docker build --tag ahmsay/microshop-account-service:1.0 .
cd ..

cd inventory-service
mvn clean package
docker build --tag ahmsay/microshop-inventory-service:1.0 .
cd ..

cd payment-service
mvn clean package
docker build --tag ahmsay/microshop-payment-service:1.0 .
cd ..

cd order-service
mvn clean package
docker build --tag ahmsay/microshop-order-service:1.0 .
cd ..

cd discovery-server
mvn clean package
docker build --tag ahmsay/microshop-discovery-server:1.0 .
cd ..

cd config-server
mvn clean package
docker build --tag ahmsay/microshop-config-server:1.0 .
cd ..
