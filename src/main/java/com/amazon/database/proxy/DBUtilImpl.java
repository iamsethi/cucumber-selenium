package com.amazon.database.proxy;

import com.amazon.enums.TestEnvironment;
import com.amazon.interfaces.IDBProxy;

class DBUtilImpl implements IDBProxy {

	public DBUtilImpl(TestEnvironment environment) {
		// DB Connection
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