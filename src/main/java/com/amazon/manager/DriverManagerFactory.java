package com.amazon.manager;

import com.amazon.enums.DriverType;

// Need to implement the other browsers and create a switch
public class DriverManagerFactory {

	public static DriverManager getManager(DriverType type) {

		DriverManager driverManager;

		switch (type) {
		case CHROME:
			driverManager = new ChromeDriverManager();
			break;
		case FIREFOX:
			driverManager = new ChromeDriverManager();
			break;
		default:
			driverManager = new ChromeDriverManager();
			break;
		}
		return driverManager;

	}
}