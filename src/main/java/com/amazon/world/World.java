package com.amazon.world;

import org.openqa.selenium.WebDriver;

import com.amazon.dataProviders.ConfigFileReader;
import com.amazon.dataProviders.JsonDataReader;
import com.amazon.databases.MYSQLJDBCConnection;
import com.amazon.interfaces.Chrome;
import com.google.inject.Inject;

import cucumber.runtime.java.guice.ScenarioScoped;

@ScenarioScoped
public class World {

	@Inject
	public JsonDataReader data;

	@Inject
	@Chrome
	public WebDriver driver;

	/*
	 * It'll look up in all modules from cucumber.properties with return type as
	 * WebDriver and find below
	 * 
	 * public WebDriver getDriver(DriverManager driverManager) { return
	 * driverManager.getDriver(); }
	 */

	@Inject
	public ConfigFileReader config;
	/*
	 * It'll inject all the modules from cucumber.properties into ConfigFileReader
	 * class and then ConfigFileReader class will use key value pair using below
	 * syntax
	 * 
	 * @Inject
	 * 
	 * @Named("URL") private String URL;
	 */

	@Inject
	public MYSQLJDBCConnection db;

	@Inject
	public RestUtilities api;

	@Inject
	public ScenarioContext scenarioContext;

}
