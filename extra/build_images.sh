cd ..

cd account-service
mvn clean package
docker build --tag ahmsay/microshop:account-service .
cd ..

cd inventory-service
mvn clean package
docker build --tag ahmsay/microshop:inventory-service .
cd ..

cd payment-service
mvn clean package
docker build --tag ahmsay/microshop:payment-service .
cd ..

cd order-service
mvn clean package
docker build --tag ahmsay/microshop:order-service .
cd ..

cd discovery-server
mvn clean package
docker build --tag ahmsay/microshop:discovery-server .
cd ..

cd config-server
mvn clean package
docker build --tag ahmsay/microshop:config-server .
cd ..

