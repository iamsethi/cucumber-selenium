package com.amazon.manager;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;

public class DriverModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DriverManager.class).annotatedWith(Names.named("Chrome")).to(ChromeDriverManager.class)
				.in(Scopes.SINGLETON);
		bind(DriverManager.class).annotatedWith(Names.named("Firefox")).to(FirefoxDriverManager.class)
				.in(Scopes.SINGLETON);
	}


}