FROM ahmsay/microshop-base
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["jre/bin/java","-jar","/app.jar"]
