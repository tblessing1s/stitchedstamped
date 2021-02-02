FROM gradle:jdk11 as BUILD

WORKDIR /app

COPY . .

USER root

RUN gradle clean test build --no-daemon --console plain


FROM adoptopenjdk:11-jre-hotspot as RUNTIME
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY build/libs/stitchedstamped-0.0.1-SNAPSHOT.jar stitchedstamped.jar
EXPOSE 8086
#ENTRYPOINT exec java $JAVA_OPTS -jar stitchedstamped.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar stitchedstamped.jar
