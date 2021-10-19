FROM adoptopenjdk/openjdk14:alpine-jre
ARG JAR_FILE=build/libs/ExchangeRate-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]