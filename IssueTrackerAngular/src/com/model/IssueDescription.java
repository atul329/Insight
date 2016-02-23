package com.model;

import java.io.Serializable;
import java.util.Date;

public class IssueDescription implements Serializable  {

	private static final long serialVersionUID = 1L;
	private int issueId;
	private Date issueLogDate;
	private UPSSTeam upssTeam;
	private String issueName;
	private String issueDesc;
	private String status;
	private String pendingWith;
	private String defectId;
	private String closedDate;
	private String fixTimeLine;
	private  IssueMaster issueMaster;
	private Date sysUpdateDate;
	private String remarks;

	public int getIssueId() {
		return issueId;
	}
	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}
	public Date getIssueLogDate() {
		return issueLogDate;
	}
	public void setIssueLogDate(Date issueLogDate) {
		this.issueLogDate = issueLogDate;
	}
	public String getIssueName() {
		return issueName;
	}
	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}
	public String getIssueDesc() {
		return issueDesc;
	}
	public void setIssueDesc(String issueDesc) {
		this.issueDesc = issueDesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPendingWith() {
		return pendingWith;
	}
	public void setPendingWith(String pendingWith) {
		this.pendingWith = pendingWith;
	}
	public String getDefectId() {
		return defectId;
	}
	public void setDefectId(String defectId) {
		this.defectId = defectId;
	}
	public String getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}
	public String getFixTimeLine() {
		return fixTimeLine;
	}
	public void setFixTimeLine(String fixTimeLine) {
		this.fixTimeLine = fixTimeLine;
	}
	public IssueMaster getIssueMaster() {
		return issueMaster;
	}
	public void setIssueMaster(IssueMaster issueMaster) {
		this.issueMaster = issueMaster;
	}
	public UPSSTeam getUpssTeam() {
		return upssTeam;
	}
	public void setUpssTeam(UPSSTeam upssTeam) {
		this.upssTeam = upssTeam;
	}
	public Date getSysUpdateDate() {
		return sysUpdateDate;
	}
	public void setSysUpdateDate(Date sysUpdateDate) {
		this.sysUpdateDate = sysUpdateDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}	
}