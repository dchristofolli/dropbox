FROM openjdk:11
ENV JAVA_OPTS=""
ADD out/artifacts/dropbox_jar/ /app.jar
LABEL AUTHOR="Daniel Christofolli"
EXPOSE  8080:8080 2021:2021
CMD java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar