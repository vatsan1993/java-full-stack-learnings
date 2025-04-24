# install OS and jdk
# clubbed image of them
# FROM openjdk:8-jdk-alpine

# if using war pacakging, we need tomcat
# FROM tomcat:10.1-jdk17 # debian/ubuntu dist

# alpine based 
FROM tomcat:10.1-jdk17-temurin-alpine



# Clean default webapps
RUN rm -rf /usr/local/tomcat/webapps/*


# creating jar file manually.
# later we will use jenkins to do this.


# copy jar / war file.
# war
 COPY target/inventory-management-spring-application-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
 
 # for the app with embedded server, you can copy
 # it anywhere in the container, you can name the app anything that you like
 
 
 
 
 
 
 