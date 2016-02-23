package com.model;

public class SMEIssueSummary {

	private UPSSTeam upssTeam;
	private String status;
	private String trackerType;
	private int issueCount;
	public UPSSTeam getUpssTeam() {
		return upssTeam;
	}
	public void setUpssTeam(UPSSTeam upssTeam) {
		this.upssTeam = upssTeam;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getTrackerType() {
		return trackerType;
	}
	public void setTrackerType(String trackerType) {
		this.trackerType = trackerType;
	}
	public int getIssueCount() {
		return issueCount;
	}
	public void setIssueCount(int issueCount) {
		this.issueCount = issueCount;
	}
}
