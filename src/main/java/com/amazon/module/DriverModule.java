package com.amazon.module;

import com.amazon.driver.ChromeDriverManager;
import com.amazon.driver.DriverManager;
import com.amazon.driver.FirefoxDriverManager;
import com.amazon.interfaces.Chrome;
import com.amazon.interfaces.Firefox;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class DriverModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DriverManager.class).annotatedWith(Names.named("Chrome")).to(ChromeDriverManager.class)
				.in(Scopes.SINGLETON);

		bind(DriverManager.class).annotatedWith(Names.named("Firefox")).to(FirefoxDriverManager.class)
				.in(Scopes.SINGLETON);

	}

	@Provides
	@Chrome
	public DriverManager getChromeDriverManager(@Named("Chrome") DriverManager driverManager) {
		return driverManager;
	}

	@Provides
	@Firefox
	public DriverManager getFirefoxDriverManager(@Named("Firefox") DriverManager driverManager) {
		return driverManager;
	}

}
