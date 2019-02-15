package com.amazon.module;

import org.openqa.selenium.WebDriver;

import com.amazon.driver.ChromeDriverManager;
import com.amazon.driver.DriverManager;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;

public class DriverModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DriverManager.class).to(ChromeDriverManager.class).in(Scopes.SINGLETON);

	}

	@Provides
	public WebDriver getDriver(DriverManager driverManager) {
		return driverManager.getDriver();
	}

}