cd ../..

cd account
mvn clean compile jib:dockerBuild
cd ..

cd inventory
mvn clean compile jib:dockerBuild
cd ..

cd order
mvn clean compile jib:dockerBuild
cd ..

cd payment
mvn clean compile jib:dockerBuild
cd ..
