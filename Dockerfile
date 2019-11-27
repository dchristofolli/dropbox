FROM openjdk:11
COPY ./build/libs/Main-1.jar /usr/src/dropbox/app.jar
WORKDIR /usr/src/dropbox
LABEL AUTHOR="Daniel Christofolli"
EXPOSE  8080:8080 2021:2021
ENTRYPOINT ["java","-jar","app.jar"]