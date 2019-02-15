package com.amazon.world;

import org.openqa.selenium.WebDriver;

import com.amazon.dataProviders.ConfigFileReader;
import com.amazon.databases.MYSQLJDBCConnection;
import com.google.inject.Inject;

import cucumber.runtime.java.guice.ScenarioScoped;

@ScenarioScoped
public class World {

	@Inject
	public WebDriver driver;

	@Inject
	public ConfigFileReader config;

	@Inject
	public MYSQLJDBCConnection db;

}
