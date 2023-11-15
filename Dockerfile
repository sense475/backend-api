FROM gradle:jdk17-jammy AS build

WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . /home/gradle/src

RUN gradle build --no-daemon

FROM openjdk:17.0.2-jdk-buster

WORKDIR /app

# LABEL maintainer=""

ENV JAVA_OPTS="-Xmx500m -Xms256m -XshowSettings:vm"

COPY --from=build /home/gradle/src/build/libs/backend-api-0.0.1-SNAPSHOT.jar /app/spring-boot-application.jar

ADD ./entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh

ENTRYPOINT /app/entrypoint.sh