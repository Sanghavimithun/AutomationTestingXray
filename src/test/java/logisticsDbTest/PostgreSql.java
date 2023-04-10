package logisticsDbTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.BrowserConfigGUI;

public class PostgreSql extends BrowserConfigGUI {
	
private static Logger log = LogManager.getLogger(BrowserConfigGUI.class.getName());

// Connection object
static Connection con = null;

// Statement object
private static Statement stmt;

//Constant for Database URL
public static String DB_URL = "jdbc:postgresql://test-mfg-db-cluster.cluster-cc7tvcrs2tnk.us-east-2.rds.amazonaws.com:5432/dmtest1";

//Database Username
public static String DB_USER = "qa_test";

//Database Password
public static String DB_PASSWORD = "1234";

@BeforeTest
public void setUp() throws Exception {
try{

// Database connection
String dbClass = "org.postgresql.Driver";

Class.forName(dbClass).getDeclaredConstructor().newInstance();

// Get connection to DB
Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

// Statement object to send the SQL statement to the Database
stmt = con.createStatement();
}
catch (Exception e)
{
e.printStackTrace();
}
}

@Test
public void test() throws SQLException {
	
	//String query1 = "SELECT COUNT(*) from mes.fs_installation";
	String query = "Select *from mes.log_files";
	ResultSet rs = stmt.executeQuery(query);
	ResultSetMetaData rsmd = rs.getMetaData();
	int columnsNumber = rsmd.getColumnCount();
	while (rs.next()) {
		for (int i = 1; i <= columnsNumber; i++) {
	      if (i > 1) System.out.print(",  ");
	      String columnValue = rs.getString(i);
	      
	      System.out.print(columnValue + " " + rsmd.getColumnName(i));
	      
	  }
	      System.out.println("");
	}
}

@AfterTest
public void tearDown() throws Exception {
// Close DB connection
if (con != null) {
con.close();
}
}
}