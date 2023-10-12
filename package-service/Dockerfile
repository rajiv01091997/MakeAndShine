FROM openjdk:11
ADD ./target/package-service-0.0.1-SNAPSHOT.jar /usr/src/package-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "package-service-0.0.1-SNAPSHOT.jar"]