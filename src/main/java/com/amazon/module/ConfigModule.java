package com.amazon.module;

import java.io.IOException;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class ConfigModule extends AbstractModule {

	@Override
	protected void configure() {

		try {
			Properties props = new Properties();
			props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
			Names.bindProperties(binder(), props);
		} catch (IOException e) {
			// skip
		}

	}
}