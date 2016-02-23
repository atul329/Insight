package com.model;

public class UPSSTeam implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private int teamId;
	private String teamName;
	private String teamDesc;
	private String lead;
	private String backUpLead;
	private String contactNo;
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
	public String getTeamDesc() {
		return teamDesc;
	}
	public void setTeamDesc(String teamDesc) {
		this.teamDesc = teamDesc;
	}
	public String getLead() {
		return lead;
	}
	public void setLead(String lead) {
		this.lead = lead;
	}
	public String getBackUpLead() {
		return backUpLead;
	}
	public void setBackUpLead(String backUpLead) {
		this.backUpLead = backUpLead;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}	
}
