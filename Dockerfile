FROM openjdk

WORKDIR /app

COPY target/sistema-api-0.0.1-SNAPSHOT.jar /app/sistema-api.jar

ENTRYPOINT ["java", "-jar", "sistema-api.jar"]