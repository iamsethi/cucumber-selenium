package com.amazon.support;

import org.openqa.selenium.WebDriver;

import com.amazon.dataProviders.ConfigFileReader;
import com.amazon.dataProviders.JsonDataReader;
import com.amazon.enums.DriverType;
import com.amazon.manager.DriverManager;
import com.amazon.manager.DriverManagerFactory;

import cucumber.runtime.java.guice.ScenarioScoped;

// Scenario scoped it is used to show Guice
// what will be the shared classes/variables and instantiate them only in here
@ScenarioScoped
public class World {
	public DriverManager driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
	public WebDriver driver = driverManager.getDriver();
	public ConfigFileReader property = new ConfigFileReader();
	public JsonDataReader data = new JsonDataReader();
	public Wait wait = new Wait(driver);
}
