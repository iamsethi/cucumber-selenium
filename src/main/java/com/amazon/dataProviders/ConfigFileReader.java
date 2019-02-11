package com.amazon.dataProviders;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.amazon.enums.DriverType;
import com.amazon.enums.EnvironmentType;

public class ConfigFileReader {
	private String baseUrl;
	private Properties properties;

	Logger log = Logger.getLogger(this.getClass());

	public void getProperties() throws IOException {
		properties = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
		properties.load(inputStream);
	}

	public long getImplicitlyWait() {
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if (implicitlyWait != null) {
			try {
				return Long.parseLong(implicitlyWait);
			} catch (NumberFormatException e) {
				throw new RuntimeException("Not able to parse value : " + implicitlyWait + " in to Long");
			}
		}
		return 30;
	}

	public String getApplicationUrl() {
		this.baseUrl = properties.getProperty("baseUrl");
		log.info(baseUrl);
		if (baseUrl != null) {
			return baseUrl;
		} else
			throw new RuntimeException(
					"Application Url not specified in the Configuration.properties file for the Key:url");
	}

	public DriverType getBrowser() {
		String browserName = properties.getProperty("browser");
		if (browserName == null || browserName.equals("chrome"))
			return DriverType.CHROME;
		else if (browserName.equalsIgnoreCase("firefox"))
			return DriverType.FIREFOX;
		else
			throw new RuntimeException(
					"Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}

	public String getTestDataResourcePath() {
		String testDataResourcePath = properties.getProperty("testDataResourcePath");
		if (testDataResourcePath != null)
			return testDataResourcePath;
		else
			throw new RuntimeException(
					"Test Data Resource Path not specified in the Configuration.properties file for the Key:testDataResourcePath");
	}

	public EnvironmentType getEnvironment() {
		String environmentName = properties.getProperty("environment");
		if (environmentName == null || environmentName.equalsIgnoreCase("local"))
			return EnvironmentType.LOCAL;
		else if (environmentName.equals("remote"))
			return EnvironmentType.REMOTE;
		else
			throw new RuntimeException(
					"Environment Type Key value in Configuration.properties is not matched : " + environmentName);
	}

	public Boolean getBrowserWindowSize() {
		String windowSize = properties.getProperty("windowMaximize");
		if (windowSize != null)
			return Boolean.valueOf(windowSize);
		return true;
	}

}
