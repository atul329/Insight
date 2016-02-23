package com.model;

public class Login implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int loginId;
	private String username;
	private String password;
	private String empName;
	private int teamId;
	private String teamName;
	private String designation;
	private int trackerId;
	
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String string) {
		this.password = string;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getTrackerId() {
		return trackerId;
	}
	public void setTrackerId(int trackerId) {
		this.trackerId = trackerId;
	}
	
	
}
