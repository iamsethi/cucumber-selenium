########################Windows- Hyper V settings ,imp Restart docker after that########################
Open Hyper-V Manager (Windows search : “Hyper-V …”)
Go to Virtual Switch Manager on the right side.
Go to DockerNAT then choose Connection type -> to External network -> #which interface you deside.
Restart docker

########################3 important Maven Plugin########################
maven-compiler-plugin - to use java verion 1.8
maven-dependency-plugin - to copy External dependencies into lib folder to give functionalities of selenium,rest assured etc.
maven-jar-plugin - to create jar file also from src/test/java because from dev perspective maven only creates jar of src/main/java

so when we run mvn clean package it'll generate below 2 files under target folder
cucumber-selenium-docker.jar - it'll contain src/main/java i.e Page Objects etc
cucumber-selenium-docker-tests.jar - it'll contain src/test/java i.e cucumber steps Test classes

Also, src/main/resources
Maven copies by default all the files and folders that are located in your /src/main/resources folder to your build folder and locates them in the root of your compiled classpath files.
if you have for example a file called configuration.properties located in /src/main/resources/configuration.properties then when running mvn clean compile this file will be copied to your /target/classes/configuration.properties

java Test.java => Test.class
javac Test 	=> It'll give output

########################Without Docker File and Jenkinsfile.Doing through terminal########################
[1]ENTRYPOINT in Dockerfile 

Make sure to traverse to target folder in terminal then 
 C:\Users\ketan.sethi\git\cucumber\cucumber-selenium\target
$ java -cp cucumber-selenium-docker.jar;cucumber-selenium-docker-tests.jar;libs/* -DBROWSER=chrome org.junit.runner.JUnitCore com.amazon.runner.RunnerTest

IMP. - To run this make sure we need to run user.dir from current code because we are currently till target folder and user.dir will give the present working directory till target and then it'll fail

But here we need all the test classes of jar cucumber-selenium-docker.jar and cucumber-selenium-docker-tests.jar
so java -cp means classpath says incluse all the above test classes from these jar files and then run com.amazon.runner.RunnerTest

[2]docker.build("iamsethi/cucumber-selenium") in Jenkinsfile 

Make sure to traverse to project root folder
(i) mvn clean package
(ii)docker build -t=iamsethi/cucumber-selenium-docker .
(iii)docker run -it --entrypoint=/bin/sh iamsethi/cucumber-selenium-docker
(iv)Now we are in Linux so separated by colon
/usr/share/udemy # java -cp cucumber-selenium-docker.jar:cucumber-selenium-docker-tests.jar:libs/* -DBROWSER=chrome org.junit.runner.JUnitCore com.amazon.runner.RunnerTest
(iv) Connect to localhost:12127 [localhost/127.0.0.1] failed: Connection refused (Connection refused)
.because selenium grid is not running in this container
.we have to give the address where selenium grid is running
.we cannot find hub because it is running as separate container

(v)Setting up Selenium grid architecture
Run command in terminal => docker-compose up will bring selenium grid infra at http://localhost:4444/

###docker-compose up -d
docker-compose down
docker-compose up -d --scale chrome=2 --scale firefox=2

Linux - ifconfig
WIndows - ipconfig

Connect using VNC - 127.0.0.1:32775
password is secret

###OR ZALENIUM , if using Zalenium then we don't need docker-compose.yaml

###Pull docker-selenium
docker pull elgalu/selenium
    
###Pull Zalenium
docker pull dosel/zalenium
    
###Run it!
docker run --rm -ti --name zalenium -p 4444:4444 -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/seluser/videos --privileged dosel/zalenium start
      
###Point your tests to 
http://localhost:4444/wd/hub and run them

###Stop
docker stop zalenium


########################(vi)Also we can start from here after doing any change in code########################
mvn clean package -DHUB_HOST=http://localhost:4444/wd/hub -DBROWSER=chrome -Dcucumber.options="--tags @regression"
imp. use localhost here because we are running locally

(vii) Repeat Step(ii) 

(viii)Repeat Step (iii) and then 
/usr/share/udemy # java -cp cucumber-selenium-docker.jar:cucumber-selenium-docker-tests.jar:libs/* -DHUB_HOST=http://192.168.1.7:4444/wd/hub -DBROWSER=chrome -Dcucumber.options="--tags @regression" org.junit.runner.JUnitCore com.amazon.runner.RunnerTest

imp .use use ipaddress here because we are running inside Container 

OR 

another (viii)
if we don't to do interactive terminal 

docker run -e HUB_HOST=http://192.168.1.7:4444/wd/hub -e BROWSER=chrome  -e TAG=@regression iamsethi/cucumber-selenium-docker  org.junit.runner.JUnitCore com.amazon.runner.RunnerTest

########################Reading reports do volume mapping########################

docker run -e HUB_HOST=http://192.168.1.7:4444/wd/hub -e BROWSER=firefox -e TAG=@regression  -v C:/Users/ketan.sethi/git/cucumber/cucumber-selenium/output:/usr/share/udemy/target/ iamsethi/cucumber-selenium-docker org.junit.runner.JUnitCore com.amazon.runner.RunnerTest

########################java -cp -consider all the classes in jar file########################
java -cp cucumber-selenium-docker.jar:cucumber-selenium-docker-tests.jar:libs/* mvn clean test

########################Remove Docker Image########################
docker rm -f containerId
docker rmi Image iamsethi/cucumber-selenium-docker
docker system prune

########################Jenkins########################
1.Setup Jenkins
docker run -p 8080:8080 -p 50000:50000 -v "C:/Users/ketan.sethi/Jenkins:/var/jenkins_home" jenkins/jenkins:lts
[volume mapping is neccessary because if we delete the container then next it'll pick from C:/Users/ketan.sethi the previous configurations]

Go to localhost:8080 and install suggested plugins

2- Setup Master Slave

(i)Click on Build Executor Status->New Node -> Some Name with Permanent Agent-> #of executors(3) && Root Directory -> C:/Users/ketan.sethi/Jenkins(It'll work as Slave workspace) && Launch Method -> Launch agent via Java Web Start

java -jar agent.jar -jnlpUrl http://localhost:8080/computer/DOCKER1/slave-agent.jnlp -secret b5d06587680ea31ea9c4545f1ff5dad1614a3d3cc0c842ac16cf3ae1a5fc6b7a -workDir "C:\Users\ketan.sethi\Jenkins"

(ii)Also we don't want anything to execute on Master -> Go to Master -> Configure -> #executors -> 0 

3- Add DockerHub credentials

Jenkins->Credential->System->GLobal Credentials
username - iamsethi786
password- 
id - dockerhub

4. Create new job - Pipeline

Definition - Pipeline script from SCM-> SCM Git ->Repo URL ->Additional Behaviour ->Clean before checkout->Script path ->Jenkinsfile->Apply and Save