package com.amazon.manager;

import java.io.File;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeDriverManager extends DriverManager {

	private ChromeDriverService service;

	@Override
	public void startService() {
		if (null == service) {
			try {
				String driverPath = "src/test/resources/drivers/chromedriver";
				if (System.getProperty("os.name").contains("Windows")) {
					driverPath += ".exe";
				}
				service = new ChromeDriverService.Builder().usingDriverExecutable(new File(driverPath))
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
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.merge(capabilities);
		driver = new ChromeDriver(service, options);
	}

}