package com.amazon.module;

import org.openqa.selenium.WebDriver;

import com.amazon.manager.ChromeDriverManager;
import com.amazon.manager.DriverManager;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

public class DriverModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DriverManager.class).to(ChromeDriverManager.class).in(Scopes.SINGLETON);

	}

	/*
	 * bind(DriverManager.class) .annotatedWith(Chrome.class)
	 * .to(ChromeDriverManager.class); is same as
	 * 
	 * @Provides
	 * 
	 * @Chrome public DriverManager provideDriverManager() { return new
	 * ChromeDriverManager(); }
	 */

	@Provides
	public WebDriver getDriver(DriverManager driverManager) {
		return driverManager.getDriver();
	}
}