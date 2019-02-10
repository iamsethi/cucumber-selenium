package com.amazon.helper;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBoxOrRadioButtonHelper {

	private WebDriver driver;
	Logger Log = Logger.getLogger(this.getClass());

	public CheckBoxOrRadioButtonHelper(WebDriver driver) {
		this.driver = driver;
		Log.debug("CheckBoxOrRadioButtonHelper : " + this.driver.hashCode());
	}

	public void selectCheckBox(WebElement element) {
		if (!isIselected(element))
			element.click();
		Log.info(element);
	}

	public void unSelectCheckBox(WebElement element) {
		if (isIselected(element))
			element.click();
		Log.info(element);
	}

	public void selectCheckBox(By locator) {
		Log.info(locator);
		selectCheckBox((WebElement) driver.findElement(locator));
	}

	public void unSelectCheckBox(By locator) {
		Log.info(locator);
		unSelectCheckBox((WebElement) driver.findElement(locator));
	}

	public boolean isIselected(By locator) {
		Log.info(locator);
		return isIselected((WebElement) driver.findElement(locator));
	}

	public boolean isIselected(WebElement element) {
		boolean flag = element.isSelected();
		Log.info(flag);
		return flag;
	}

}
