FROM jboss/wildfly
MAINTAINER Falk Harnisch (docker@falkharnisch.de)

RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#1234 --silent

ADD customization /opt/jboss/wildfly/customization/

RUN /opt/jboss/wildfly/customization/execute.sh

VOLUME /opt/jboss/wildfly/standalone/deployments

EXPOSE 9990

ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
