package com.amazon.dataProviders;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class ConfigFileReader {

	Logger log = Logger.getLogger(this.getClass());

	@Inject
	@Named("baseUrl")
	private String url;

	@Inject
	@Named("environment")
	private String envn;

	@Inject
	@Named("browser")
	private String browser;

	@Inject
	@Named("windowMaximize")
	private String windowMaximize;

	@Inject
	@Named("implicitlyWait")
	private String implicitlyWait;

	@Inject
	@Named("envtype")
	private String envtype;

	@Inject
	@Named("reportConfigPath")
	private String reportConfigPath;

	public String getUrl() {
		return url;
	}

	public String getEnvn() {
		return envn;
	}

	public String getBrowser() {
		return browser;
	}

	public String getWindowMaximize() {
		return windowMaximize;
	}

	public String getImplicitlyWait() {
		return implicitlyWait;
	}

	public String getEnvtype() {
		return envtype;
	}

	public String getReportConfigPath() {
		return reportConfigPath;
	}

}
