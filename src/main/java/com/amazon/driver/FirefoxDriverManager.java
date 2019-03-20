package com.amazon.driver;

import java.net.URL;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirefoxDriverManager extends DriverManager {

	@Override
	public void createDriver() {
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		FirefoxOptions options = new FirefoxOptions();
		options.merge(capabilities);
		try {
			if (System.getProperty("HUB_HOST") != null) {
				String host = System.getProperty("HUB_HOST");
				driver = new RemoteWebDriver(new URL(host), capabilities);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}