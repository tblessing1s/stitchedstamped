FROM gradle:jdk11 as BUILD

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle gradlew $APP_HOME
COPY gradle $APP_HOME/gradle
RUN ./gradlew build || return 0

FROM adoptopenjdk:11-jre-hotspot as RUNTIME

COPY --from=BUILD build/libs/*.jar stitchedstamped.jar

WORKDIR /app

EXPOSE 8086

ENV APP_FILES="/app"
#ENTRYPOINT exec java $JAVA_OPTS -jar stitchedstamped.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
CMD java -jar app.jar -javaagent:agent.jar
