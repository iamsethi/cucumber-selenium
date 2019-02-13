package com.amazon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.amazon.interfaces.DataBaseHelper;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class MYSQLJDBCConnection {

	@Inject
	private DataBaseHelper dataBaseHelper;

	@Inject
	@Named("idr.db.username")
	private String user;

	@Inject
	@Named("idr.db.password")
	private String pswd;

	@Inject
	@Named("idr.db.url")
	private String db_url;

	public void getConnection() throws Exception {
		// dataBaseHelper.executeQuery(sqlQuery);

		String host = "localhost";
		String port = "3306";
		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/db_example", "root",
				"root");
		Statement s = con.createStatement();

		ResultSet rs = s.executeQuery("SELECT * FROM TRADE_LETTER;");
		while (rs.next()) {
			System.out.println(rs.getString("TRADE_LETTER_ID"));
		}
	}

}