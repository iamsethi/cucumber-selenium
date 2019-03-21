@Inject
	public WebDriver driver;
will go to Driver Module and will run below method
@Provides
	public WebDriver getDriver(DriverProvider driverProvider) {
		return driverProvider.get(System.getProperty("BROWSER")).get().getDriver();
	}

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
