FROM openjdk:20-jdk-slim
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
COPY target/fipe-dryve-0.0.1-SNAPSHOT.jar fipedryve.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar fipedryve.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar fipedryve.jar
