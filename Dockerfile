#FROM ubuntu:latest
FROM eclipse-temurin:17
#LABEL authors="stevenbutz"
WORKDIR /home
COPY ./target/prime-service2-0.0.1-SNAPSHOT.jar primes-service2.jar
ENTRYPOINT ["java","-jar","primes-service2.jar"]
#ENTRYPOINT ["top", "-b"]