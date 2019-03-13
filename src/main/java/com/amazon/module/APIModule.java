package com.amazon.module;

import java.io.IOException;
import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class APIModule extends AbstractModule {

	@Override
	protected void configure() {
		try {
			Properties api = new Properties();
			api.load(getClass().getClassLoader().getResourceAsStream("api.properties"));
			Names.bindProperties(binder(), api);
		} catch (IOException e) {
			// skip
		}

	}
}