FROM openjdk:21-jdk-slim
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} sicoin.jar
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod", "/sicoin.jar"]