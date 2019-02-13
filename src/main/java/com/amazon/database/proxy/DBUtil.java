package com.amazon.database.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.amazon.enums.TestEnvironment;
import com.amazon.interfaces.IDBProxy;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class DBUtil implements IDBProxy {

	private IDBProxy dbUtil;
	private static final List<TestEnvironment> restrictEnvironmentList;

	// restrict DB access for the below environments
	static {
		restrictEnvironmentList = new ArrayList<>();
		restrictEnvironmentList.add(TestEnvironment.PROD);
		restrictEnvironmentList.add(TestEnvironment.STAGING);
	}

	@Inject
	public DBUtil(@Named("test.environment") String testEnvironment) {
		if (!restrictEnvironmentList.contains(TestEnvironment.valueOf(testEnvironment))) {
			dbUtil = new DBUtilImpl();
		}
	}

	@Override
	public void insertUser() {
		if (Objects.nonNull(dbUtil)) {
			dbUtil.insertUser();
		}
	}

	@Override
	public void deleteUser() {
		if (Objects.nonNull(dbUtil)) {
			dbUtil.deleteUser();
		}
	}

}