FROM centos:7

MAINTAINER Ramesh Rajendran<ramesh.rajendran@github.com>

# Upgrading system
RUN yum -y upgrade
RUN yum -y install wget
# Downloading Java
ENV JAVA_VERSION 8u31
ENV BUILD_VERSION b13

RUN wget --no-cookies --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jdk/$JAVA_VERSION-$BUILD_VERSION/jdk-$JAVA_VERSION-linux-x64.rpm" -O /tmp/jdk-8-linux-x64.rpm

RUN yum -y install /tmp/jdk-8-linux-x64.rpm

RUN alternatives --install /usr/bin/java jar /usr/java/latest/bin/java 200000
RUN alternatives --install /usr/bin/javaws javaws /usr/java/latest/bin/javaws 200000
RUN alternatives --install /usr/bin/javac javac /usr/java/latest/bin/javac 200000

ENV JAVA_HOME /usr/java/latest

#Download tomcat
RUN curl -0 http://www-us.apache.org/dist/tomcat/tomcat-8/v8.0.36/bin/apache-tomcat-8.0.36.tar.gz | tar -zx
RUN mv apache-tomcat-8.0.36 /opt/tomcat
ADD tomcat-users.xml /opt/tomcat/conf
#Running tomcat
CMD ["/opt/tomcat/bin/catalina.sh", "run"]

