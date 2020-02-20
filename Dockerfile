FROM openjdk:8-jre-alpine
COPY target/hello-static-view-0.0.1-SNAPSHOT.jar /service.jar
CMD /usr/bin/java -jar /service.jar
