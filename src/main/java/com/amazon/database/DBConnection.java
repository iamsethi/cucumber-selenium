package com.amazon.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {

		// TODO Auto-generated method stub

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