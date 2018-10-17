#!/bin/bash
cd /opt/tomcat/apache-tomcat-8.5.34/webapps
sudo rm -rf store-2.0.3.RELEASE
sudo rm -rf classes
sudo rm -rf generated-sources 
sudo rm -rf maven-status 
sudo rm store-2.0.3.RELEASE.war
cd ~/Dropbox/Fall2018/CIS4930-WebbApps/projects/1/IndividualProject1/store
rm -rf target/
mvn clean compile war:exploded
cd target/
sudo mv store-2.0.3.RELEASE/ /opt/tomcat/apache-tomcat-8.5.34/webapps 
sudo mv classes /opt/tomcat/apache-tomcat-8.5.34/webapps 
sudo mv generated-sources /opt/tomcat/apache-tomcat-8.5.34/webapps
sudo mv maven-status /opt/tomcat/apache-tomcat-8.5.34/webapps  
sudo tomcatdown
sudo tomcatup
