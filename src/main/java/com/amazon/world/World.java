package com.amazon.world;

import org.openqa.selenium.WebDriver;

import com.amazon.dataProviders.ConfigFileReader;
import com.amazon.dataProviders.ConfigModule;
import com.amazon.dataProviders.JsonDataReader;
import com.amazon.database.DBConnection;
import com.amazon.database.DBModule;
import com.amazon.database.DBUtilProxy;
import com.amazon.interfaces.DBUtil;
import com.amazon.manager.DriverManager;
import com.amazon.manager.DriverModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class World {

	Injector driverInjector = Guice.createInjector(new DriverModule()); // Bind Chromedriver to drivermanager
	public DriverManager driverManager = driverInjector.getInstance(DriverManager.class);
	public WebDriver driver = driverManager.getDriver();

	public JsonDataReader data = new JsonDataReader();
	public Wait wait = new Wait(driver);

	// Initialize config properties file
	Injector configInjector = Guice.createInjector(new ConfigModule());
	// Use above key value in DBConnection
	public ConfigFileReader config = configInjector.getInstance(ConfigFileReader.class);
	// Initialize DB properties file
	Injector dbInjector = Guice.createInjector(new DBModule());
	// Use above key value in DBConnection
	public DBConnection dbService = dbInjector.getInstance(DBConnection.class);
	// Proxy Pattern + Use above K,V in contructor of
	// DBUtilProxy
	public DBUtil dbUtil = dbInjector.getInstance(DBUtilProxy.class);

}
