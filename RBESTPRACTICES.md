####Chapter 3
 Tests should not include any Selenium WebDriver method or objects
 Tests should use objects and methods of the HomePage and ResultsPage classes
 Tests should use JUNIT assertions for verifications
 Tests should use the @Test annotation
 The driver object is created in the setUp() method (with the @Before annotation)
 The driver object is closed in the tearDown() method (with the @After annotation)
 A page object class gets the driver object as a parameter of the constructor				- DONE
 A page object class has variables (for state) and methods (for behavior)					- DONE
 The Selenium WebDriver methods and objects should be used only inside of the page classes	- DONE

####Chapter 4
 If a page method changes the current page in the browser, the page method should return
an object for the new page
 Tests should create as few page objects as possible