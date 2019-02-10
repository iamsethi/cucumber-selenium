package com.amazon.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextBoxHelper extends GenericHelper {

	private WebDriver driver;
	Logger Log = Logger.getLogger(this.getClass());

	public TextBoxHelper(WebDriver driver) {
		super(driver);
		this.driver = driver;
		Log.debug("TextBoxHelper : " + this.driver.hashCode());
	}

	public void sendKeys(By locator, String value) {
		Log.info("Locator : " + locator + " Value : " + value);
		getElement(locator).sendKeys(value);
	}

	public void clear(By locator) {
		Log.info("Locator : " + locator);
		getElement(locator).clear();
	}

	public String getText(By locator) {
		Log.info("Locator : " + locator);
		return getElement(locator).getText();
	}

	public void clearAndSendKeys(By locator, String value) {
		WebElement element = getElement(locator);
		element.clear();
		element.sendKeys(value);
		Log.info("Locator : " + locator + " Value : " + value);
	}

}
