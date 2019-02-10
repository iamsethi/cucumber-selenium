package com.amazon.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ButtonHelper {

	private WebDriver driver;
	Logger Log = Logger.getLogger(this.getClass());

	public ButtonHelper(WebDriver driver) {
		this.driver = driver;
		Log.debug("Button Helper : " + this.driver.hashCode());
	}

	public void click(By locator) {
		click((WebElement) driver.findElement(locator));
		Log.info(locator);
	}

	public void click(WebElement element) {
		element.click();
		Log.info(element);
	}
}
