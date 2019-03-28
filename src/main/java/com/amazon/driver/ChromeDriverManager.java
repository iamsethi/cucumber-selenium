package com.amazon.driver;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeDriverManager extends DriverManager {

	@Override
	public void createDriver() {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName("chrome");
		capabilities.setVersion("73.0");
		capabilities.setCapability("enableVNC", true);
		capabilities.setCapability("enableVideo", false);

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