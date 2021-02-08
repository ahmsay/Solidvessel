cd ..

cd account-service
mvn clean package
docker build --tag ahmsay/microshop-account-service:latest .
cd ..

cd inventory-service
mvn clean package
docker build --tag ahmsay/microshop-inventory-service:latest .
cd ..

cd payment-service
mvn clean package
docker build --tag ahmsay/microshop-payment-service:latest .
cd ..

cd order-service
mvn clean package
docker build --tag ahmsay/microshop-order-service:latest .
cd ..

cd discovery-server
mvn clean package
docker build --tag ahmsay/microshop-discovery-server:latest .
cd ..

cd config-server
mvn clean package
docker build --tag ahmsay/microshop-config-server:latest .
cd ..
