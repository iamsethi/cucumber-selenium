package com.amazon.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.amazon.interfaces.IDBProxy;
import com.amazon.interfaces.IDataBaseHelper;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class MYSQLJDBCConnection implements IDBProxy {

	@Override
	public void insertUser() {
		System.out.println("inserting new user");
	}

	@Override
	public void deleteUser() {
		System.out.println("deleting user");
	}

	@Inject
	private IDataBaseHelper dataBaseHelper;

	@Inject
	@Named("idr.db.username")
	private String user;

	@Inject
	@Named("idr.db.password")
	private String pswd;

	@Inject
	@Named("idr.db.url")
	private String db_url;

	@Inject
	@Named("database.driver")
	private String driver;

	@Inject
	@Named("select.all.sql")
	private String select_query;

	public void getConnection() {
		try {
			dataBaseHelper.setConnectionString(db_url);

			Class.forName(driver);
			Connection con = DriverManager.getConnection(db_url, user, pswd);
			Statement s = con.createStatement();

			ResultSet rs = s.executeQuery(select_query);
			while (rs.next()) {
				System.out.println(rs.getString("TRADE_LETTER_ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}