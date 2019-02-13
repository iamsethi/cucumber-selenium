package com.amazon.database;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class DBConnection {

	public String getUser() {
		return user;
	}

	public String getPswd() {
		return pswd;
	}

	public String getDb_url() {
		return db_url;
	}

	@Inject
	@Named("idr.db.username")
	private String user;

	@Inject
	@Named("idr.db.password")
	private String pswd;

	@Inject
	@Named("idr.db.url")
	private String db_url;

}