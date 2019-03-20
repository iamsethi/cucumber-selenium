FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

# Workspace
WORKDIR /usr/share/udemy

# ADD .jar under target from host
# into this image
ADD target/cucumber-selenium-docker-tests.jar			cucumber-selenium-docker-tests.jar
ADD target/cucumber-selenium-docker.jar 				cucumber-selenium-docker.jar
ADD target/libs											libs

# in case of any other dependency like .csv / .json / .xls
# please ADD that as well
ADD src/main/resources									src/main/resources
ADD src/test/resources									src/test/resources



# ADD health check script
#ADD healthcheck.sh                      			healthcheck.sh

# BROWSER
# HUB_HOST


ENTRYPOINT java -cp cucumber-selenium-docker.jar:cucumber-selenium-docker-tests.jar:libs/* -DHUB_HOST=$HUB_HOST -DBROWSER=$BROWSER org.junit.runner.JUnitCore com.amazon.runner.RunnerTest