FROM openjdk:12-jdk

MAINTAINER Falk Harnisch <docker@falkharnisch.de>

RUN apt-get -y update && \
    apt-get install -y maven git

VOLUME /root/.m2

COPY start.sh /
ENTRYPOINT  ["/start.sh"]
