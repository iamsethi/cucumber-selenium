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
import com.google.inject.Key;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

//Guice-constructor of class will be mapped with configure method  -  IMP
@Singleton
public class World {
	// Bind Chromedriver/Firefoxdriver to drivermanager
	Injector driverInjector = Guice.createInjector(new DriverModule());
	// Pass bowser name
	public DriverManager driverManager = driverInjector
			.getInstance(Key.get(DriverManager.class, Names.named("Firefox")));
	public WebDriver driver = driverManager.getDriver();

	public JsonDataReader data = new JsonDataReader();
	public Wait wait = new Wait(driver);

	// Initialize config properties file
	Injector configInjector = Guice.createInjector(new ConfigModule());
	// Use above key value in ConfigFileReader class
	public ConfigFileReader config = configInjector.getInstance(ConfigFileReader.class);

	// Initialize DB properties file
	Injector dbInjector = Guice.createInjector(new DBModule());
	// Use above key value in DBConnection class
	public DBConnection dbService = dbInjector.getInstance(DBConnection.class);
	// Proxy Pattern + Use above K,V in constructor of DBUtilProxy
	public DBUtil dbUtil = dbInjector.getInstance(DBUtilProxy.class);

}
