package com.amazon.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GenericHelper {

	private WebDriver driver;
	Logger log = Logger.getLogger(this.getClass());

	public GenericHelper(WebDriver driver) {
		this.driver = driver;
		log.debug("GenericHelper : " + this.driver.hashCode());
	}

	public WebElement getElement(By locator) {
		log.info(locator);
		if (IsElementPresent(locator))
			return driver.findElement(locator);

		try {
			throw new NoSuchElementException("Element Not Found : " + locator);
		} catch (RuntimeException re) {
			log.error(re);
			throw re;
		}
	}

	/**
	 * Check for element is present based on locator If the element is present
	 * return the web element otherwise null
	 * 
	 * @param locator
	 * @return WebElement or null
	 */

	public WebElement getElementWithNull(By locator) {
		log.info(locator);
		try {
			return driver.findElement(locator);
		} catch (NoSuchElementException e) {
			// Ignore
		}
		return null;
	}

	public boolean IsElementPresent(By locator) {
		boolean flag = driver.findElements(locator).size() >= 1;
		log.info(flag);
		return flag;
	}

	public String takeScreenShot() {
		log.info("");
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
	}

}
