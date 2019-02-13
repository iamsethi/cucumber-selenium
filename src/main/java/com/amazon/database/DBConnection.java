package com.amazon.database;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class DBConnection {

	@Inject
	@Named("idr.db.username")
	private String user;

	public String getUser() {
		return user;
	}

}