package com.amazon.databases;

import java.io.IOException;
import java.util.Properties;

import com.amazon.interfaces.IDataBaseHelper;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class DBModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(IDataBaseHelper.class).to(DBHelper.class);
		try {
			Properties prop1 = new Properties();
			Properties prop2 = new Properties();
			prop1.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
			prop2.load(getClass().getClassLoader().getResourceAsStream("application-sql.properties"));
			Names.bindProperties(binder(), prop1);
			Names.bindProperties(binder(), prop2);
		} catch (IOException e) {
			// log.error("Could not load config: ", e);
			System.exit(1);
		}

	}
}