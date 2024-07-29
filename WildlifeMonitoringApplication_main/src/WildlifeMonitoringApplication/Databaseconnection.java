package WildlifeMonitoringApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


	//Class representing a database connection
	public class Databaseconnection {
	     // JDBC driver path for MySQL
		private static final String driver_path = "com.mysql.cj.jdbc.Driver";
		// Database connection URL
		private static final String url = "jdbc:mysql://localhost:3306/WildlifeMonitoringdb";
		private static final String userName = "root";
		private static final String passWord = "root";

		public  Databaseconnection() {
			try {
				Class.forName(driver_path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public static Connection getConnection() throws SQLException {
			return DriverManager.getConnection(url, userName, passWord);
		}

		public PreparedStatement prepareStatement(String query) {
			// TODO Auto-generated method stub
			return null;
		}

	}







