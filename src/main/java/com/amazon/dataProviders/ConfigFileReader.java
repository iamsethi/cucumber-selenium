package com.amazon.dataProviders;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class ConfigFileReader {

	Logger log = Logger.getLogger(this.getClass());

	@Inject
	@Named("URL")
	private String URL;

	@Inject
	@Named("TEST_ENVIRONMENT")
	private String TEST_ENVIRONMENT;

	public String getURL() {
		return URL;
	}

	public String getTEST_ENVIRONMENT() {
		return TEST_ENVIRONMENT;
	}

	public String getLOG_LEVEL() {
		return LOG_LEVEL;
	}

	public String getBROWSER() {
		return BROWSER;
	}

	public boolean isRECORD_VIDEO() {
		return RECORD_VIDEO;
	}

	public boolean isCLOSE_BROWSER() {
		return CLOSE_BROWSER;
	}

	public boolean isMAXIMIZE_BROWSER() {
		return MAXIMIZE_BROWSER;
	}

	public boolean isUSE_GRID() {
		return USE_GRID;
	}

	public boolean isLOCAL() {
		return LOCAL;
	}

	@Inject
	@Named("LOG_LEVEL")
	private String LOG_LEVEL;

	@Inject
	@Named("BROWSER")
	private String BROWSER;

	@Inject
	@Named("RECORD_VIDEO")
	private boolean RECORD_VIDEO;

	@Inject
	@Named("CLOSE_BROWSER")
	private boolean CLOSE_BROWSER;

	@Inject
	@Named("MAXIMIZE_BROWSER")
	private boolean MAXIMIZE_BROWSER;

	@Inject
	@Named("USE_GRID")
	private boolean USE_GRID;

	@Inject
	@Named("LOCAL")
	private boolean LOCAL;

}
