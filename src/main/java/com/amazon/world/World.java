package com.amazon.world;

import org.openqa.selenium.WebDriver;

import com.amazon.dataProviders.ConfigFileReader;
import com.amazon.dataProviders.ConfigModule;
import com.amazon.dataProviders.JsonDataReader;
import com.amazon.database.DBConnection;
import com.amazon.database.DBModule;
import com.amazon.database.DBUtilProxy;
import com.amazon.enums.DriverType;
import com.amazon.interfaces.DBUtil;
import com.amazon.manager.DriverManager;
import com.amazon.manager.DriverManagerFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class World {
	public DriverManager driverManager = DriverManagerFactory.getManager(DriverType.CHROME); // Factory Pattern
	public WebDriver driver = driverManager.getDriver();

	public JsonDataReader data = new JsonDataReader();
	public Wait wait = new Wait(driver);

	Injector configInjector = Guice.createInjector(new ConfigModule()); // Initialize config properties file
	public ConfigFileReader config = configInjector.getInstance(ConfigFileReader.class); // Use above key value in
																							// DBConnection

	Injector dbInjector = Guice.createInjector(new DBModule()); // Initialize DB properties file
	public DBConnection dbService = dbInjector.getInstance(DBConnection.class); // Use above key value in DBConnection
	public DBUtil dbUtil = dbInjector.getInstance(DBUtilProxy.class); // Proxy Pattern + Use above K,V in DBUtilProxy

}
