FROM openjdk:8-jdk-alpine
VOLUME /opt/app
COPY build/libs/lumpas-0.0.1-SNAPSHOT.jar loompas.jar
#ENTRYPOINT ["java","-jar","/loompas.jar"]