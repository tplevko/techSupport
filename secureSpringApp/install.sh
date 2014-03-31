#!/bin/bash

USER=tplevko
DEST=/home/${USER}
TOMCAT_HOME=${DEST}/java/servers/tomcat/apache-tomcat-7.0.50
PROJECT_HOME=${DEST}/git/secureApp/secureSpringApp
EAR_FILE_NAME="secureAppExample-web-1.0-SNAPSHOT.war"

function build_project() {
   cd ${PROJECT_HOME}
   ## mvn clean install -Dmaven.test.skip=true
   mvn clean install
}

function deploy_project() {
   cd ${TOMCAT_HOME}/webapps
   ## sh asadmin deploy --force=true ${PROJECT_HOME}/secureAppExample-web/target/${EAR_FILE_NAME}
   cp ${PROJECT_HOME}/secureAppExample-web/target/${EAR_FILE_NAME} .
   cd ../bin
   ./shutdown.sh
   ./startup.sh
}

build_project

deploy_project

exit 0

