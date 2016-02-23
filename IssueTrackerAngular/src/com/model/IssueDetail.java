package com.model;

import java.util.Date;

public class IssueDetail {

	private Date sysUpdateDate;
	private String remarks;
    private IssueDescription issueDescription;
    private String pendingWith;
    private Login login;
	private String priority;
	private String frequency;
	private String deployed;
	private String count;
	private String ticket;
	private String effort;
    private int issueDetailId;
	public Date getSysUpdateDate() {
		return sysUpdateDate;
	}
	public void setSysUpdateDate(Date sysUpdateDate) {
		this.sysUpdateDate = sysUpdateDate;
	}
	public IssueDescription getIssueDescription() {
		return issueDescription;
	}
	public void setIssueDescription(IssueDescription issueDescription) {
		this.issueDescription = issueDescription;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPendingWith() {
		return pendingWith;
	}
	public void setPendingWith(String pendingWith) {
		this.pendingWith = pendingWith;
	}
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getDeployed() {
		return deployed;
	}
	public void setDeployed(String deployed) {
		this.deployed = deployed;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getEffort() {
		return effort;
	}
	public void setEffort(String effort) {
		this.effort = effort;
	}
	public int getIssueDetailId() {
		return issueDetailId;
	}
	public void setIssueDetailId(int issueDetailId) {
		this.issueDetailId = issueDetailId;
	}
}
