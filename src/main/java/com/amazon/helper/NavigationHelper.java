package com.amazon.helper;

import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {

	private WebDriver driver;
	Logger Log = Logger.getLogger(this.getClass());

	public NavigationHelper(WebDriver driver) {
		this.driver = driver;
		Log.debug("NavigationHelper : " + this.driver.hashCode());
	}

	public void navigateTo(String url) {
		Log.info(url);
		driver.get(url);
	}

	public void naviagteTo(URL url) {
		Log.info(url.getPath());
		driver.get(url.getPath());
	}

	public String getTitle() {
		String title = driver.getTitle();
		Log.info(title);
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		Log.info(url);
		return driver.getCurrentUrl();
	}

}
