package com.amazon.driver;

import com.amazon.enums.DriverType;
import com.amazon.interfaces.Chrome;
import com.amazon.interfaces.Firefox;
import com.amazon.interfaces.IDriverProvider;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DriverProvider implements IDriverProvider {
	@Inject	@Chrome
	public Provider<DriverManager> chdriver;

	@Inject @Firefox
	public Provider<DriverManager> ffdriver;

	public Provider<DriverManager> get(DriverType type) {
		Provider<DriverManager> driver;
		switch (type) {
		case CHROME:
			driver = chdriver;
			break;
		case FIREFOX:
			driver = ffdriver;
			break;
		default:
			driver = chdriver;
			break;
		}
		return driver;
	}

	
	public Provider<DriverManager> get(String driver) {
		DriverType parsedDriver = DriverType.valueOf(driver.toUpperCase());
		return get(parsedDriver);
	}
}
