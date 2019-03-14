package com.amazon.driver;

import java.io.File;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxDriverManager extends DriverManager {

	private GeckoDriverService service;

	@Override
	public void startService() {
		if (null == service) {
			try {
				String driverPath = "src/test/resources/drivers/geckodriver";
				if (System.getProperty("os.name").contains("Windows")) {
					driverPath += ".exe";
				}
				service = new GeckoDriverService.Builder().usingDriverExecutable(new File(driverPath))
						.usingAnyFreePort().build();
				service.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void stopService() {
		if (null != service && service.isRunning())
			service.stop();
	}

	@Override
	public void createDriver() {
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		FirefoxOptions options = new FirefoxOptions();
		options.merge(capabilities);
		driver = new FirefoxDriver(service, options);
	}

}