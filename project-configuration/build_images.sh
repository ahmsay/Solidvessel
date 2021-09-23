cd base-image
docker build --tag ahmsay/microshop-base .
cd ..
cd ..

cd account-service
mvn clean compile jib:dockerBuild
cd ..

cd inventory-service
mvn clean compile jib:dockerBuild
cd ..

cd payment-service
mvn clean compile jib:dockerBuild
cd ..

cd order-service
mvn clean compile jib:dockerBuild
cd ..

cd discovery-server
mvn clean compile jib:dockerBuild
cd ..

cd gateway-service
mvn clean compile jib:dockerBuild
cd ..
