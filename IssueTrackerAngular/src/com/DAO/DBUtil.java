package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	
	private static String URL = "";
	private static String DRIVER_NAME = "oracle.jdbc.OracleDriver";
	private static String USERNAME = "";
	private static String PASSWORD = "";
	
	public static Connection getConnection() {
		Connection con = null;
		try {
            Class.forName(DRIVER_NAME);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
		public static void closeConnection(Connection con) {

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		public static void closePreparedStatement(PreparedStatement ps) {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		public static void closeResultSet(ResultSet rs) {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
}
}
