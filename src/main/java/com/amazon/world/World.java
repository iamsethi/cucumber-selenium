package com.amazon.world;

import org.openqa.selenium.WebDriver;

import com.amazon.dataProviders.ConfigFileReader;
import com.amazon.dataProviders.JsonDataReader;
import com.amazon.database.proxy.DBUtil;
import com.amazon.databases.MYSQLJDBCConnection;
import com.amazon.interfaces.IDBProxy;
import com.amazon.manager.DriverManager;
import com.amazon.module.ConfigModule;
import com.amazon.module.DBModule;
import com.amazon.module.DriverModule;
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
	public DriverManager driverManager = driverModule.getInstance(Key.get(DriverManager.class, Names.named("Firefox")));
	public WebDriver driver = driverManager.getDriver();

	public JsonDataReader data;

	// Initialize config properties file
	Injector config_properties = Guice.createInjector(new ConfigModule());
	// Use above key value in ConfigFileReader class
	public ConfigFileReader config = config_properties.getInstance(ConfigFileReader.class);

	// Initialize db.properties in src/test/resources and application-sql.properties
	// in src/main/resources
	Injector db_module = Guice.createInjector(new DBModule());
	// Use above key value in DBConnection class
	public MYSQLJDBCConnection sql = db_module.getInstance(MYSQLJDBCConnection.class);
	// Proxy Pattern + Use above K,V in constructor of DBUtilProxy
	public IDBProxy dbUtil = db_module.getInstance(DBUtil.class);

}
