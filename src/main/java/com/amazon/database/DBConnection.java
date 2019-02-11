package com.amazon.database;

public class DBConnection {



	/*public  void runTest() {

		try {

			// Retrieve database connection properties from the properties file
			String driver = world.property.getProperty("db.driver");
			String dburl = DBDrivenProperties.getProperty("db.url");
			String dbname = DBDrivenProperties.getProperty("db.dbname");
			String dbquery = DBDrivenProperties.getProperty("db.query");
			String dbuser = DBDrivenProperties.getProperty("db.username");
			String dbpassword = DBDrivenProperties.getProperty("db.password");

			// Load the MySQL JDBC driver
			Class.forName(driver);

			// Create a connection to the MySQL database
			Connection conn = DriverManager.getConnection(dburl + dbname, dbuser, dbpassword);

			// Create a statement to be executed
			Statement stmt = conn.createStatement();

			// Execute the query
			ResultSet rs = stmt.executeQuery(dbquery);

			// Loop through the query results and run the REST service test for every row
			while (rs.next()) {
				String zipcode = rs.getString("zipcode");
				String city = rs.getString("city");
				String state = rs.getString("state");
				try {
					testService(zipcode, city, state);
				} catch (IOException | JSONException e) {
					System.out.println(e.toString());
				}
			}

			// Close the database connection
			conn.close();

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.toString());
		}
	}*/
}