##################Selenoid on GKE##################
secret must on the moon.yaml



##################Zalenium on GKE##################
64 vCPU and 305GB

        - '--desiredContainers'
        - '100'
        - '--maxDockerSeleniumContainers'
        - '100'
        
https://console.cloud.google.com/kubernetes

https://github.com/zalando/zalenium/tree/f19f4c16d2ba986b4e519daae4752cf066836742/docs/k8s/gke


gcloud container clusters get-credentials zalenium
kubectl create clusterrolebinding iamsethi --clusterrole=cluster-admin --user=ketansethi786@gmail.com
kubectl apply -f plumbing.yaml
kubectl apply -f pv.yaml
kubectl config set-context $(kubectl config current-context) --namespace=zalenium
kubectl apply -f zalenium.yaml
gcloud compute firewall-rules create zalenium --allow tcp:30000-32767

kubectl get pods
kubectl get svc

kubectl delete -f pv.yaml && kubectl delete -f zalenium.yaml
kubectl apply -f pv.yaml && kubectl apply -f zalenium.yaml

How much memory does your machine have? 32 containers seems like quite a lot given you’re not using kubernetes. If you make the desiredContainers 10 does it work?

I suspect if you want the scale you’re after you’re going to need to switch to kubernetes. When I run zalenium in kubernetes I assign 1Gb per selenium node and 1/2 a cpu which for you would be 32 GB ram and 16 cores. Which is a pretty large machine. And that’s ignoring zalenium itself and anything else running on the server.


##################Rakefile##################
  found_tests = CukeSlicer::Slicer.new.slice(test_directory, filters, :file_line)
  found_tests.each do |test|
    puts test.split(/features)[1] 
        #/Amazon/Shopping.feature:34
    #/home/ketan/git/JenkinsSlave/workspace/SELENIUM_DOCKER_RUNNER/src/test/resources/features/Amazon/Shopping.feature:34
end

     


###sudo usermod -a -G docker $USER
###sudo for slave agent as well
###Pipeline Utility Steps Plugin
Navigate to jenkins > Manage jenkins > In-process Script Approval
There was a pending command, which I had to approve

sudo apt update
sudo apt install ruby-full
gem sources -a http://rubygems.org
gem sources -r https://rubygems.org
sudo gem install cuke_slicer
sudo gem install rake

##################returnStdout##################
scenarioCount = sh(returnStdout: true, script: scenarioCountCmd)
syso in Rake file will be captured in above command

########################Windows- Hyper V settings ,imp Restart docker after that########################
Open Hyper-V Manager (Windows search : �Hyper-V ��)
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

########################Jenkins Create -  SELENIUM_DOCKERFILE_BUILDER_PUSHER ########################
Create an Alpine image with volume mapped as $HOME/.m2:/root/.m2'
Build means create a image using Dockerfile(code test classes from project in put into Alpine image) with some arbitrary name
Pusher means push to https://cloud.docker.com/
1.Setup Jenkins
docker run -p 8080:8080 -p 50000:50000 -v "/home/ketan/git/Jenkins:/var/jenkins_home" jenkins/jenkins:lts
[volume mapping is neccessary because if we delete the container then next it'll pick from C:/Users/ketan.sethi the previous configurations]

Go to localhost:8080 and install suggested plugins

2- Setup Master Slave

(i)Click on Build Executor Status->New Node -> Some Name with Permanent Agent-> #of executors(3) && Root Directory -> /home/ketan/git/JenkinsSlave(It'll work as Slave workspace) && Launch Method -> Launch agent via Java Web Start

java -jar agent.jar -jnlpUrl http://localhost:8080/computer/DOCKER1/slave-agent.jnlp -secret b5d06587680ea31ea9c4545f1ff5dad1614a3d3cc0c842ac16cf3ae1a5fc6b7a -workDir "/home/ketan/git/JenkinsSlave"

(ii)Also we don't want anything to execute on Master -> Go to Master -> Configure -> #executors -> 0 

3- Add DockerHub credentials

Jenkins->Credential->System->GLobal Credentials
username - iamsethi786
password- 
id - dockerhub

4. Create new job - Pipeline - named as SELENIUM_DOCKERFILE_BUILDER_PUSHER

Definition - Pipeline script from SCM-> SCM Git ->Repo URL ->Additional Behaviour ->Clean before checkout->Script path ->Jenkinsfile->Apply and Save


Do not clean before checkout in Job configuration
Manage Jenkins-> Script Console -> System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "") -> Run