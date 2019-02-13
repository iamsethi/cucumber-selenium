package com.amazon.world;

import org.openqa.selenium.WebDriver;

import com.amazon.dataProviders.ConfigFileReader;
import com.amazon.dataProviders.ConfigModule;
import com.amazon.dataProviders.JsonDataReader;
import com.amazon.database.proxy.DBUtil;
import com.amazon.databases.DBModule;
import com.amazon.databases.MYSQLJDBCConnection;
import com.amazon.interfaces.IDBProxy;
import com.amazon.manager.DriverManager;
import com.amazon.manager.DriverModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

//Guice-constructor[new className()] of class will be mapped with configure method  -  IMP
@Singleton
public class World {
	// Bind Chromedriver/Firefoxdriver to drivermanager
	Injector driverModule = Guice.createInjector(new DriverModule());
	// Pass bowser name
	public DriverManager driverManager = driverModule
			.getInstance(Key.get(DriverManager.class, Names.named("Firefox")));
	public WebDriver driver = driverManager.getDriver();

	public JsonDataReader data = new JsonDataReader();
	public Wait wait = new Wait(driver);

	// Initialize config properties file
	Injector config_properties = Guice.createInjector(new ConfigModule());
	// Use above key value in ConfigFileReader class
	public ConfigFileReader config = config_properties.getInstance(ConfigFileReader.class);

	// Initialize DB properties file
	Injector db_module = Guice.createInjector(new DBModule());
	// Use above key value in DBConnection class
	public MYSQLJDBCConnection sql = db_module.getInstance(MYSQLJDBCConnection.class);
	// Proxy Pattern + Use above K,V in constructor of DBUtilProxy
	public IDBProxy dbUtil = db_module.getInstance(DBUtil.class);

}
