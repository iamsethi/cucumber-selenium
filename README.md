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

docker-compose up --scale chrome=2 --scale firefox=2


Linux - ifconfig
WIndows - ipconfig

Connect using VNC - 127.0.0.1:32775
password is secret

 
########################(vi)Also we can start from here after doing any change in code########################
mvn clean package -DHUB_HOST=http://localhost:4444/wd/hub -DBROWSER=chrome -Dcucumber.options="--tags @regression"
imp. use localhost here because we are running locally

(vii) Repeat Step(ii) 

(viii)Repeat Step (iii) and then 
/usr/share/udemy # java -cp cucumber-selenium-docker.jar:cucumber-selenium-docker-tests.jar:libs/* -DHUB_HOST=http://192.168.1.7:4444/wd/hub -DBROWSER=chrome -Dcucumber.options="--tags @regression" org.junit.runner.JUnitCore com.amazon.runner.RunnerTest

imp .use use ipaddress here because we are running inside Container 

OR 

'(viii)'
if we don't to do interactive terminal 

docker run -e HUB_HOST=http://192.168.1.7:4444/wd/hub -e BROWSER=chrome  -e TAG=@api iamsethi/cucumber-selenium-docker  org.junit.runner.JUnitCore com.amazon.runner.RunnerTest

Reading reports do volume mapping

docker run -e HUB_HOST=http://192.168.1.7:4444/wd/hub -e BROWSER=firefox  -v C:/Users/ketan.sethi/git/cucumber/cucumber-selenium/output:/usr/share/udemy/target/ iamsethi/cucumber-selenium-docker org.junit.runner.JUnitCore com.amazon.runner.RunnerTest

########################java -cp -consider all the classes in jar file########################
java -cp cucumber-selenium-docker.jar:cucumber-selenium-docker-tests.jar:libs/* mvn clean test

Open Hyper-V Manager (Windows search : “Hyper-V …”)
Go to Virtual Switch Manager on the right side.
Go to DockerNAT then choose Connection type -> to External network -> #which interface you deside.
Hope that help you, too.

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

########################Singleton browser########################
the browser is singleton so everytime a same driver id is returned if use below anywhere and any no of time
log.info("Driver ID is : " + world.driver.get("firefox").get().getDriver().toString());


########################Multi browser########################
@Inject @Chrome
public WebDriver driver;
	
@Chrome is an interface created under  package com.amazon.interfaces;
it'll search for @Chrome annotation  under @Provides which it'll find in method 

@Provides
@Chrome
public WebDriver getChromeDriver(@Named("Chrome") DriverManager driverManager) {
		return driverManager.getDriver();}

but the above method has an argument of DriverManager which it'll find it under configure method

######https://stackoverflow.com/questions/42184243/the-meaning-of-bindings-annotation-with-provider-methods########################
@Provides @PayPal
  CreditCardProcessor providePayPalCreditCardProcessor(
      @Named("PayPal API key") String apiKey) {
    PayPalCreditCardProcessor processor = new PayPalCreditCardProcessor();
    processor.setApiKey(apiKey);
    return processor;
  }
  
  @Inject
  public RealBillingService(@PayPal CreditCardProcessor processor,
      TransactionLog transactionLog)
      
Think of the annotation as part of the method's return type. The @Provides method you listed does not simply provide a CreditCardProcessor, it provides a @PayPal CreditCardProcessor. Thus, the method is written @Provides @PayPal CreditCardProcessor.

You can then request @PayPal CreditCardProcessor as in your second usage, by annotating a parameter in an @Inject-annotated method or constructor, or by adding the annotation to an @Inject-annotated field. (You can also request it directly from an Injector instance by creating a Key.)


@ScenarioScoped dependency that is injected by Cucumber into your step classes are always re-created for each scenario. Your job is to make sure you dont have any static fields in the injected dependency. Static fields belongs to the class and not the instance so they are not cleared for each new instance

@Singleton - Then only 1 driver will be created for entire suite


########################Schema validation ####################################
https://jsonschema.net/ and click on Infer Schema to generate the schema
put the schema under src/test/resources/schema
use it

res.then().assertThat()
	      .body(matchesJsonSchemaInClasspath("schema/album-track.json"));

########################Scenario context ####################################
https://www.toolsqa.com/selenium-cucumber-framework/share-data-between-steps-in-cucumber-using-scenario-context/
Scenario context for tweetId between different steps

world.scenarioContext.setContext(Context.TWEET_ID, tweetId);
String tweetId=(String)world.scenarioContext.getContext(Context.TWEET_ID);

Scenario Context is a class which holds the test data information specifically. It actually use the Test Context to travel the information between various steps. With in this ScenarioContext class, you can create any number of fields to store any form of data. It stores the information in the key value pair and again, value can be of any type. It can store String, Boolean, Integer or may be a Class. Also the important point here is that the information which we store in Scenario Context is generated run time. Means during the run if you wish to store some information, you will use Scenario Context.

########################scribejava-apis - 2.5.3 only will work####################################

java.lang.NoClassDefFoundError: com/github/scribejava/core/model/Abstract Request

########################Rakuten Amazon Item search API####################################
https://scrapehero-amazon-product-info-v1.p.rapidapi.com/product-details?asin=1483359468
Header -
X-RapidAPI-Key
38456a8097msh8b40ea11c2cad67p1f4175jsn50cae3bbd864

########################CLI####################################
mvn clean install -Dcucumber.options="--tags @regression" -Dbrowser=chrome
 mvn clean install -Denvironment=stage -Dcucumber.options="--tags @stage1" -Dusername=ketan -Dbrowser=chrome
 
########################Logger####################################
Logger log = Logger.getLogger(this.getClass());

########################Factory Pattern in Creating WebDriver Instance USAGE -> world.driver####################################
PROBLEM - 
if (Browser.equals("chrome")) {
    // logic to start the driver service
    // then to create driver etc
} else if (Browser.equals("phantomjs")) {

}

It might look like an easy solution. But it is really NOT. When we have to start/stop the Driver Service ourselves, the code becomes significantly larger and very difficult to maintain

SOLUTION - 
Test classes, as the users, should not really care how the drivers are actually created. What it needs is just a WebDriver instance to execute the given test case. So we come up with our own abstract class – DriverManager – which test classes could use to get a driver instance from it and use them in their tests.

SUMMARY - 
By using Factory Pattern, we completely hide the creational logic of the browser / service instances to the test classes. If we get a new requirement to add a new browser, say PhantomJS, should not be a big deal. We just need to create a PhantomJSDriverManager which extends DriverManager. [Ofcourse you have to add PhantomJS in DriverType]

########################Hooks####################################
Hooks should be in step definition

########################Read Data from JSON####################################
RegisterEnvironment and Initialize json are static methods
All other methods are non static - accessed via -> world.data.fill....

########################Cucumber Extent Reports####################################
Use info.cukes instead of io.cucumber

########################log4j - Send emailL####################################
https://www.lifewire.com/allow-email-programs-access-to-gmail-with-password-1171875
log.error("Some text") in the test will send the email

########################Remove MySQL####################################
sudo apt-get remove --purge mysql*
sudo apt-get purge mysql*
sudo apt-get autoremove
sudo apt-get autoclean
sudo apt-get remove dbconfig-mysql
sudo apt-get dist-upgrade

######################Install MySQL############################
sudo apt-get install mysql-server

######################Start MySQL############################
sudo service mysql start
sudo systemctl status mysql

######################Setup MySQL############################
sudo mysql
create database db_example; -- Create the new database
create user 'springuser'@'localhost' identified by 'ThePassword'; -- Creates the user
grant all on db_example.* to 'springuser'@'localhost'; -- Gives all the privileges to the new user on the 
newly created database
 
USE db_example;
CREATE TABLE TRADE_LETTER(TRADE_LETTER_ID INT ,PARTY_ID INT );
INSERT INTO TRADE_LETTER VALUES (1,11);
INSERT INTO TRADE_LETTER VALUES (2,22);
SELECT * FROM TRADE_LETTER;

exit

###################DB Logic ###################################3

Properties configProps = Properties.load(getClass().getClassLoader().getResourceAsStream("myconfig.properties");
Names.bindProperties(binder(), configProps);
and voilà all your config is ready for injection:

@Provides // use this to have nice creation methods in modules
public Connection getDBConnection(@Named("dbConnection") String connectionStr,
                                  @Named("dbUser") String user,
                                  @Named("dbPw") String pw,) {
  return DriverManager.getConnection(connectionStr, user, pw);
}
Now just create your Java properties file myconfig.properties at the root of your classpath with

dbConnection = jdbc:mysql://localhost/test
dbUser = username
dbPw = password
