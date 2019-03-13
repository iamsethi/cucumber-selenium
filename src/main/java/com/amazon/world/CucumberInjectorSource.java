package com.amazon.world;

import com.amazon.module.APIModule;
import com.amazon.module.ConfigModule;
import com.amazon.module.DBModule;
import com.amazon.module.DriverModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;

import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;

public class CucumberInjectorSource implements InjectorSource {
	@Override
	public Injector getInjector() {
		return Guice.createInjector(Stage.PRODUCTION, CucumberModules.SCENARIO, new DriverModule(), new ConfigModule(),
				new DBModule(), new APIModule());
	}
}