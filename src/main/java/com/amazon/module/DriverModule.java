package com.amazon.module;

import com.amazon.manager.ChromeDriverManager;
import com.amazon.manager.DriverManager;
import com.amazon.manager.FirefoxDriverManager;
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