package com.amazon.steps;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.amazon.driver.DriverManager;
import com.amazon.world.World;
import com.cucumber.listener.Reporter;
import com.google.inject.Inject;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {
	@Inject
	private World world;

	@Inject
	public DriverManager driverManager;

	// Set the value of the properties in the support
	@Before
	public void beforeScenario() throws IOException {
		if (world.config.isMAXIMIZE_BROWSER()) {
			world.driver.manage().window().maximize();
		}
	}

	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			world.db.deleteUser();
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try {
				// This takes a screenshot from the driver at save it to the specified location
				File sourcePath = ((TakesScreenshot) world.driver).getScreenshotAs(OutputType.FILE);

				// Building up the destination path for the screenshot to save
				// Also make sure to create a folder 'screenshots' with in the cucumber-report
				// folder
				File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/"
						+ screenshotName + ".png");

				// Copy taken screenshot from source location to destination location
				FileUtils.copyFile(sourcePath, destinationPath);

				// This attach the specified screenshot to the test
				Reporter.addScreenCaptureFromPath(destinationPath.toString());
			} catch (NoSuchWindowException e) {

			} catch (IOException e) {
			}
		}
	}

	@After(order = 0)
	public void afterScenario() {
		if (world.config.isCLOSE_BROWSER()) {
			driverManager.quitDriver();
		}
	}
}
