package nounRecognizer;

import java.sql.*;
import java.util.ArrayList;

public class Database {
	public static void main(String[] args) {
		try {
			Class.forName ("org.h2.Driver");
			Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","username","password");
			
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static ArrayList<String> getAllContent(Connection conn) throws SQLException {
		String query = "SELECT * FROM USER_QUERIES";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		ArrayList<String> userQueries = new ArrayList<>();
		
		// iterate through the java resultset
		while (rs.next())
		{
			String email = rs.getString("EMAIL");
			String user_query = rs.getString("QUERY");

			// print the results
			//System.out.format("%s, %s\n", email, user_query);
			userQueries.add(user_query);
		}
		st.close();
		return userQueries;
	}

	private static void printAllUserInput(Connection conn) throws SQLException {
		String query = "SELECT * FROM USER_QUERIES";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);

		// iterate through the java resultset
		while (rs.next())
		{
			String email = rs.getString("EMAIL");
			String input = rs.getString("QUERY");

			// print the results
			System.out.format("%s, %s\n", email, input);
		}
		st.close();
	}

	private static void addRowToUsers(Connection conn, String name, String email) {
		// the mysql insert statement
		String query = "insert into users (NAME,EMAIL) values ('" + name + "', '" + email + "')";

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);

			// execute the preparedstatement
			preparedStmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void deleteRowFromUsers(Connection conn, String email) {
		// the mysql delete statement
		String query = "delete from users where EMAIL = '" + email + "'";

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt;
		try {
			preparedStmt = conn.prepareStatement(query);

			// execute the preparedstatement
			preparedStmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static int getNumberOfUsers(Connection conn) {
		String query = "select count(*) from USERS";
		try {
			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next())
			{
				int userCount = rs.getInt(1);

				return userCount;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;

	}
	
	private static String getContentFromUser(Connection conn, String email) {
		String query = "SELECT * FROM USER_QUERIES WHERE EMAIL = '" + email + "'";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			String userQuery = "";
			while (rs.next())
			{
			userQuery = rs.getString("QUERY");
			}
			
			return userQuery;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
		
	}

}
