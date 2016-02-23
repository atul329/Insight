package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	
	private static String URL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=10.40.98.19)(PORT=1532))(ADDRESS=(PROTOCOL=TCP)(HOST=10.40.98.20)(PORT=1532))(ADDRESS=(PROTOCOL=TCP)(HOST=10.40.98.21)(PORT=1532))(LOAD_BALANCE=yes)(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=UPSZ1SUB)))";
	private static String DRIVER_NAME = "oracle.jdbc.OracleDriver";
	private static String USERNAME = "SUBSCRIBER_ADMIN_RO";
	private static String PASSWORD = "sub_ro_32399";
	
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
