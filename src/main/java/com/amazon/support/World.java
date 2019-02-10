package com.amazon.support;

import org.openqa.selenium.WebDriver;

import com.amazon.dataProviders.ConfigFileReader;
import com.amazon.dataProviders.JsonDataReader;

import cucumber.runtime.java.guice.ScenarioScoped;

// Scenario scoped it is used to show Guice
// what will be the shared classes/variables and instantiate them only in here
@ScenarioScoped
public class World {
	public WebDriver driver = new DriverFactory().getManager();
	public ConfigFileReader property = new ConfigFileReader();
	public JsonDataReader data = new JsonDataReader();
	public Wait wait = new Wait(driver);
}
