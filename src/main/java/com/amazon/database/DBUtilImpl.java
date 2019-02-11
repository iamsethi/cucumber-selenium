package com.amazon.database;

import com.amazon.enums.TestEnvironment;
import com.amazon.interfaces.DBUtil;

class DBUtilImpl implements DBUtil {

	DBConnection dbConnection;

	public DBUtilImpl(TestEnvironment environment) {
		// DB connection
	}

	@Override
	public void insertUser() {
		System.out.println("inserting new user");
	}

	@Override
	public void deleteUser() {
		System.out.println("deleting user");
	}

}