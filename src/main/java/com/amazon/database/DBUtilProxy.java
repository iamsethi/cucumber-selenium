package com.amazon.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.amazon.enums.TestEnvironment;
import com.amazon.interfaces.DBUtil;

public class DBUtilProxy implements DBUtil {

	private DBUtil dbUtil;
	private static final List<TestEnvironment> restrictEnvironmentList;

	// restrict DB access for the below environments
	static {
		restrictEnvironmentList = new ArrayList<>();
		restrictEnvironmentList.add(TestEnvironment.PROD);
		restrictEnvironmentList.add(TestEnvironment.STAGING);
	}

	public DBUtilProxy(TestEnvironment testEnvironment) {
		if (!restrictEnvironmentList.contains(testEnvironment)) {
			dbUtil = new DBUtilImpl(testEnvironment);
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