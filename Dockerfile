FROM openjdk:11
LABEL AUTHOR="dchristofolli"
RUN mkdir -p /usr/src/dropbox
COPY ./ /usr/src/dropbox
WORKDIR /usr/src/dropbox
EXPOSE  8080:8080 2021:2021