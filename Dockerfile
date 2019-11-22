FROM openjdk:11
COPY /out/artifacts/dropbox_jar/dropbox.jar app.jar
WORKDIR /usr/src/dropbox
LABEL AUTHOR="Daniel Christofolli"
EXPOSE  8080:8080 2021:2021
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]