package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.model.Login;

public class LoginDAO {

	public Login getUser(String username, String password){
		Login login = new Login();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	    try{
	    	String str="select * from SME_LOGIN_TABLE where username=? and password=?";
	    	connection=DBUtil.getConnection();
	    	ps=connection.prepareStatement(str);
	    	ps.setInt(1, Integer.parseInt(username.trim()));
	    	ps.setString(2, password);
	    	rs=ps.executeQuery();
			if (!rs.isBeforeFirst()){
				System.out.println("No User with username :: "+username+" and password :: "+password);
				return login;
			}else{
				while(rs.next()){
					login.setLoginId(rs.getInt("LOGIN_ID"));
					login.setUsername(rs.getString("USERNAME"));
					login.setPassword(rs.getString("PASSWORD"));
					login.setEmpName(rs.getString("EMP_NAME"));
					login.setTeamId(rs.getInt("TEAM_ID"));
					login.setTeamName(rs.getString("TEAM_NAME"));
					login.setDesignation(rs.getString("DESIGNATION"));
					login.setTrackerId(rs.getInt("TRACKER_ID"));
				}
				insertLoginData(login.getUsername(),login.getEmpName());
			}

	    }catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(connection);
		}
		return login;
	}
	
	public Login fetchUser(int loginId){
		Login login = new Login();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	    try{
	    	String str="select * from SME_LOGIN_TABLE where LOGIN_ID=?";
	    	connection=DBUtil.getConnection();
	    	ps=connection.prepareStatement(str);
	    	ps.setInt(1, loginId);
	    	rs=ps.executeQuery();
			if (!rs.isBeforeFirst()){
				System.out.println("No User with LOGIN_ID :: "+loginId);
				return login;
			}
			while(rs.next()){
				login.setLoginId(rs.getInt("LOGIN_ID"));
				login.setUsername(rs.getString("USERNAME"));
				login.setPassword(rs.getString("PASSWORD"));
				login.setEmpName(rs.getString("EMP_NAME"));
				login.setTeamId(rs.getInt("TEAM_ID"));
				login.setTeamName(rs.getString("TEAM_NAME"));
				login.setDesignation(rs.getString("DESIGNATION"));
				login.setTrackerId(rs.getInt("TRACKER_ID"));
			}
	    }catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(connection);
		}
		return login;
	}
	
	public Login getUserDetails(String username){
		Login login = new Login();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	    try{
	    	String str="select * from SME_LOGIN_TABLE where USERNAME=?";
	    	connection=DBUtil.getConnection();
	    	ps=connection.prepareStatement(str);
	    	ps.setString(1, username);
	    	rs=ps.executeQuery();
			if (!rs.isBeforeFirst()){
				System.out.println("No User with Username :: "+username);
				return login;
			}
			while(rs.next()){
				login.setLoginId(rs.getInt("LOGIN_ID"));
				login.setUsername(rs.getString("USERNAME"));
				login.setPassword(rs.getString("PASSWORD"));
				login.setEmpName(rs.getString("EMP_NAME"));
				login.setTeamId(rs.getInt("TEAM_ID"));
				login.setTeamName(rs.getString("TEAM_NAME"));
				login.setDesignation(rs.getString("DESIGNATION"));
				login.setTrackerId(rs.getInt("TRACKER_ID"));
			}
	    }catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(connection);
		}
		return login;
	}
	
	public boolean changePassword(String newPassword, Login login){
		int count=0;
		Connection connection = null;
		PreparedStatement ps = null;
		String str="update SME_LOGIN_TABLE set PASSWORD=? where LOGIN_ID=?";
		try{
			connection=DBUtil.getConnection();
			ps=connection.prepareStatement(str);
			ps.setString(1, newPassword);
			ps.setInt(2, login.getLoginId());
			count=ps.executeUpdate();
			connection.commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(connection);
		}
		return (count>0);
	}
	public boolean changePassword(String newPassword, int loginId){
		int count=0;
		Connection connection = null;
		PreparedStatement ps = null;
		String str="update SME_LOGIN_TABLE set PASSWORD=? where LOGIN_ID=?";
		try{
			connection=DBUtil.getConnection();
			ps=connection.prepareStatement(str);
			ps.setString(1, newPassword);
			ps.setInt(2, loginId);
			count=ps.executeUpdate();
			connection.commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(connection);
		}
		return (count>0);
	}
	public boolean checkUser(int loginId, String password){
		boolean flag = false;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	    try{
	    	String str="select * from SME_LOGIN_TABLE where LOGIN_ID=? and password= ?";
	    	connection=DBUtil.getConnection();
	    	ps=connection.prepareStatement(str);
	    	ps.setInt(1, loginId);
	    	ps.setString(2, password);
	    	rs=ps.executeQuery();
			if (!rs.isBeforeFirst()){
				System.out.println("Password is wrong");
				return flag;
			}else{
				return true;
			}
	    }catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(connection);
		}
		return flag;
	}
	public ArrayList<Login> getAllUser(){
		ArrayList<Login> loginList = new ArrayList<Login>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	    try{
	    	String str="select * from SME_LOGIN_TABLE";
	    	connection=DBUtil.getConnection();
	    	ps=connection.prepareStatement(str);
	    	rs=ps.executeQuery();
			if (!rs.isBeforeFirst()){
				System.out.println("No User exist");
				return loginList;
			}else{
				while(rs.next()){
					Login login = new Login();
					login.setLoginId(rs.getInt("LOGIN_ID"));
					login.setUsername(rs.getString("USERNAME"));
					login.setPassword(rs.getString("PASSWORD"));
					login.setEmpName(rs.getString("EMP_NAME"));
					login.setTeamId(rs.getInt("TEAM_ID"));
					login.setTeamName(rs.getString("TEAM_NAME"));
					login.setDesignation(rs.getString("DESIGNATION"));
					login.setTrackerId(rs.getInt("TRACKER_ID"));
					loginList.add(login);
				}
			}
	    }catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(connection);
		}
		return loginList;
	}
	
	public static void insertLoginData(String username, String name){
		Connection connection = null;
		PreparedStatement ps = null;
		String str = "insert into sme_login_audit values (sysdate,?,?)";
		try{
			connection = DBUtil.getConnection();
			ps = connection.prepareStatement(str);
			ps.setString(1, username);
			ps.setString(2, name);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(connection);
		}
		
		
	}
}
