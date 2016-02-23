package com.model;

import java.util.Date;

public class IssueMaster implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int mId;
	private Date sysCreationDate;
	private String issueType;
	
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public Date getSysCreationDate() {
		return sysCreationDate;
	}
	public void setSysCreationDate(Date sysCreationDate) {
		this.sysCreationDate = sysCreationDate;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
}
