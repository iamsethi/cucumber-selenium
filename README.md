@ScenarioScoped dependency that is injected by Cucumber into your step classes are always re-created for each scenario. Your job is to make sure you dont have any static fields in the injected dependency. Static fields belongs to the class and not the instance so they are not cleared for each new instance

@Singleton - Then only 1 driver will be created for entire suite


########################Rakuten Amazon Item search API####################################
https://scrapehero-amazon-product-info-v1.p.rapidapi.com/product-details?asin=1483359468
Header -
X-RapidAPI-Key
38456a8097msh8b40ea11c2cad67p1f4175jsn50cae3bbd864

########################CLI####################################
 mvn clean test -Dcucumber.options="--tags @1483359468"
 
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
All other methods are non statice - accessed via -> world.data.fill....

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

###################After Hook ###################################3
