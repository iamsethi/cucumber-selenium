package com.amazon.manager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;

public class DriverModule extends AbstractModule {

	@Override
	protected void configure() {
		// DriverManager config
		bind(DriverManager.class).to(ChromeDriverManager.class).in(Scopes.SINGLETON);
	}

	@Provides
	public WebDriver getDriver(DriverManager driverManager) {
		return driverManager.getDriver();
	}

	@Provides
	public Actions getActions(WebDriver driver) {
		return new Actions(driver);
	}

	@Provides
	public JavascriptExecutor getExecutor(WebDriver driver) {
		return (JavascriptExecutor) (driver);
	}
}