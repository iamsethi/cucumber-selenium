package com.amazon.database;

import java.io.IOException;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class DBModule extends AbstractModule {

	@Override
	protected void configure() {
		try {
			Properties props = new Properties();
			props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
			Names.bindProperties(binder(), props);
		} catch (IOException e) {
			// log.error("Could not load config: ", e);
			System.exit(1);
		}

	}
}