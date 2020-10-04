cd ..

cd account-service
mvn clean package
docker build --tag ahmsay/online-shopping-application:account-service .
cd ..

cd inventory-service
mvn clean package
docker build --tag ahmsay/online-shopping-application:inventory-service .
cd ..

cd payment-service
mvn clean package
docker build --tag ahmsay/online-shopping-application:payment-service .
cd ..

cd order-service
mvn clean package
docker build --tag ahmsay/online-shopping-application:order-service .
cd ..

cd discovery-server
mvn clean package
docker build --tag ahmsay/online-shopping-application:discovery-server .
cd ..

cd config-server
mvn clean package
docker build --tag ahmsay/online-shopping-application:config-server .
cd ..

