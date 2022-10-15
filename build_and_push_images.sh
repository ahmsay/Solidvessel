cd auth
mvn clean compile jib:build
cd ..

cd account
mvn clean compile jib:build
cd ..

cd inventory
mvn clean compile jib:build
cd ..

cd order
mvn clean compile jib:build
cd ..

cd payment
mvn clean compile jib:build
cd ..
