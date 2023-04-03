package logisticsGilbertSftpTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SOSftpDatabase {

	// Connection object
	static Connection con = null;

	// Statement object
	private static Statement stmt;

	// Constant for Database URL
	public static String DB_URL = "jdbc:postgresql://test-mfg-db-cluster.cluster-cc7tvcrs2tnk.us-east-2.rds.amazonaws.com:5432/dmtest1";

	// Database Username
	public static String DB_USER = "qa_test";

	// Database Password
	public static String DB_PASSWORD = "1234";

	// method to set up the database connection
	public void setUp() throws Exception {
		try {
			String dbClass = "org.postgresql.Driver"; // Database connection
			Class.forName(dbClass).getDeclaredConstructor().newInstance();
			Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // Get connection to DB
			stmt = con.createStatement(); // Statement object to send the SQL statement to the Database
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// method to verify the orders based on current date, here we are passing the
	// current date as created_at
	// so that while testing we will truncate all the tables and retrieve the date
	// only for the current date

	public void verifyData(String created_at) throws SQLException {
		try {
			Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // Get connection to DB
			stmt = con.createStatement();

			// String query = "select * from mes.log_files union mes.log_confirmation_data
			// where created_at >= (select '"+ created_at +"'::timestamp::date) ";
			String q1 = "select distinct file_name from mes.log_confirmation_data l join mes.log_files lg on l.file_name=lg.name where l.created_at >= (select '"
					+ created_at + "'::timestamp::date)";

			ResultSet rs = stmt.executeQuery(q1);
			ResultSetMetaData rsmd = rs.getMetaData();

			int columnsNumber = rsmd.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					if (i > 1)
						System.out.print(",  ");
					String columnValue = rs.getString(i);
					System.out.print(columnValue + " " + rsmd.getColumnName(i));
				}
				System.out.println("");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}

	}

}