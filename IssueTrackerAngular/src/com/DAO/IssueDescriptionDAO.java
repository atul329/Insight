package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.model.IssueDescription;
import com.model.IssueMaster;
import com.model.SMEIssueSummary;
import com.model.UPSSTeam;

public class IssueDescriptionDAO {

	public boolean addIssue(IssueDescription issueDescription) {
		boolean isAdded = false;
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String str = "insert into SME_ISSUE_Description (ISSUE_ID,ISSUE_LOG_DATE,ISSUE_NAME,ISSUE_DESCRIPTION,STATUS,PENDING_WITH,DEFECT_ID,CLOSED_DATE,TEAM_ID,FIX_TIMELINE,M_ID,SYS_UPDATE_DATE,REMARKS) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(str);

			java.sql.Date logDate = new java.sql.Date(issueDescription
					.getIssueLogDate().getTime());
			ps.setInt(1, issueDescription.getIssueId());
			ps.setDate(2, logDate);
			ps.setString(3, issueDescription.getIssueName());
			ps.setString(4, issueDescription.getIssueDesc());
			ps.setString(5, issueDescription.getStatus());
			ps.setString(6, issueDescription.getPendingWith());
			ps.setString(7, issueDescription.getDefectId());
			ps.setString(8, issueDescription.getClosedDate());
			ps.setInt(9, issueDescription.getUpssTeam().getTeamId());
			ps.setString(10, issueDescription.getFixTimeLine());
			ps.setInt(11, issueDescription.getIssueMaster().getmId());
			ps.setDate(12, logDate);
			ps.setString(13, issueDescription.getRemarks());
			count = ps.executeUpdate();
			con.commit();
			isAdded = count > 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}

		return isAdded;
	}

	public ArrayList<IssueDescription> getTeamIssues(int teamId, int trackerId) {
		ArrayList<IssueDescription> issueDescList = new ArrayList<IssueDescription>();
		IssueMasterDAO issueMasterDAO = new IssueMasterDAO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<IssueMaster> issueMasters = issueMasterDAO.getAllTracker();
		UPSSTeamDAO teamDAO = new UPSSTeamDAO();
		ArrayList<UPSSTeam> upssTeamList = teamDAO.getAllTeams();
		String str = "select * from SME_ISSUE_DESCRIPTION where team_id = ? and m_id=? and STATUS <> 'CLOSED' order by SYS_UPDATE_DATE desc";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(str);
			ps.setInt(1, teamId);
			ps.setInt(2, trackerId);
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("No Open issue for team id :: " + teamId);
				return issueDescList;
			}
			while (rs.next()) {
				IssueDescription issueDescription = new IssueDescription();
				issueDescription.setIssueId(rs.getInt("ISSUE_ID"));
				for (UPSSTeam upssTeam : upssTeamList) {
					if (upssTeam.getTeamId() == rs.getInt("TEAM_ID")) {
						issueDescription.setUpssTeam(upssTeam);
						break;
					}
				}
				issueDescription.setIssueLogDate(rs.getDate("ISSUE_LOG_DATE"));
				issueDescription.setIssueName(rs.getString("ISSUE_NAME"));
				issueDescription
						.setIssueDesc(rs.getString("ISSUE_DESCRIPTION"));
				issueDescription.setStatus(rs.getString("STATUS"));
				issueDescription.setClosedDate(rs.getString("CLOSED_DATE"));
				issueDescription.setDefectId(rs.getString("DEFECT_ID"));
				issueDescription.setFixTimeLine(rs.getString("FIX_TIMELINE"));
				issueDescription.setPendingWith(rs.getString("PENDING_WITH"));
				issueDescription.setRemarks(rs.getString("REMARKS"));
				for (IssueMaster issueMaster : issueMasters) {
					if (issueMaster.getmId() == rs.getInt("M_ID")) {
						issueDescription.setIssueMaster(issueMaster);
						break;
					}
				}
				issueDescription
						.setSysUpdateDate(rs.getDate("SYS_UPDATE_DATE"));
				issueDescList.add(issueDescription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return issueDescList;
	}

	public ArrayList<IssueDescription> getAllIssues(int trackerId) {
		ArrayList<IssueDescription> issueDescList = new ArrayList<IssueDescription>();
		IssueMasterDAO issueMasterDAO = new IssueMasterDAO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<IssueMaster> issueMasters = issueMasterDAO.getAllTracker();
		UPSSTeamDAO teamDAO = new UPSSTeamDAO();
		ArrayList<UPSSTeam> upssTeamList = teamDAO.getAllTeams();
		String str = "select * from SME_ISSUE_DESCRIPTION where m_id=? and STATUS <> 'CLOSED' order by SYS_UPDATE_DATE desc";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(str);
			ps.setInt(1, trackerId);
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("No Open issues");
				return issueDescList;
			}
			while (rs.next()) {
				IssueDescription issueDescription = new IssueDescription();
				issueDescription.setIssueId(rs.getInt("ISSUE_ID"));
				for (UPSSTeam upssTeam : upssTeamList) {
					if (upssTeam.getTeamId() == rs.getInt("TEAM_ID")) {
						issueDescription.setUpssTeam(upssTeam);
						break;
					}
				}
				issueDescription.setIssueLogDate(rs.getDate("ISSUE_LOG_DATE"));
				issueDescription.setIssueName(rs.getString("ISSUE_NAME"));
				issueDescription
						.setIssueDesc(rs.getString("ISSUE_DESCRIPTION"));
				issueDescription.setStatus(rs.getString("STATUS"));
				issueDescription.setClosedDate(rs.getString("CLOSED_DATE"));
				issueDescription.setDefectId(rs.getString("DEFECT_ID"));
				issueDescription.setFixTimeLine(rs.getString("FIX_TIMELINE"));
				issueDescription.setPendingWith(rs.getString("PENDING_WITH"));
				issueDescription.setRemarks(rs.getString("REMARKS"));
				for (IssueMaster issueMaster : issueMasters) {
					if (issueMaster.getmId() == rs.getInt("M_ID")) {
						issueDescription.setIssueMaster(issueMaster);
						break;
					}
				}
				issueDescription
						.setSysUpdateDate(rs.getDate("SYS_UPDATE_DATE"));
				issueDescList.add(issueDescription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return issueDescList;
	}

	public IssueDescription fetchIssueDescription(int issueId) {
		IssueDescription issueDescription = new IssueDescription();
		IssueMasterDAO issueMasterDAO = new IssueMasterDAO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<IssueMaster> issueMasters = issueMasterDAO.getAllTracker();
		UPSSTeamDAO teamDAO = new UPSSTeamDAO();
		ArrayList<UPSSTeam> upssTeamList = teamDAO.getAllTeams();
		String str = "select * from SME_ISSUE_DESCRIPTION where issue_id = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(str);
			ps.setInt(1, issueId);
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("No issue for issue id :: " + issueId);
				return issueDescription;
			}
			while (rs.next()) {
				issueDescription.setIssueId(rs.getInt("ISSUE_ID"));
				for (UPSSTeam upssTeam : upssTeamList) {
					if (upssTeam.getTeamId() == rs.getInt("TEAM_ID")) {
						issueDescription.setUpssTeam(upssTeam);
						break;
					}
				}
				issueDescription.setIssueLogDate(rs.getDate("ISSUE_LOG_DATE"));
				issueDescription.setIssueName(rs.getString("ISSUE_NAME"));
				issueDescription.setIssueDesc(rs.getString("ISSUE_DESCRIPTION"));
				issueDescription.setStatus(rs.getString("STATUS"));
				issueDescription.setClosedDate(rs.getString("CLOSED_DATE"));
				issueDescription.setDefectId(rs.getString("DEFECT_ID"));
				issueDescription.setFixTimeLine(rs.getString("FIX_TIMELINE"));
				issueDescription.setPendingWith(rs.getString("PENDING_WITH"));
				issueDescription.setRemarks(rs.getString("REMARKS"));
				for (IssueMaster issueMaster : issueMasters) {
					if (issueMaster.getmId() == rs.getInt("M_ID")) {
						issueDescription.setIssueMaster(issueMaster);
						break;
					}
				}
				issueDescription
						.setSysUpdateDate(rs.getDate("SYS_UPDATE_DATE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return issueDescription;
	}

	public boolean updateIssueDesciption(String pendingWith, String defectId,
			String status, String closedDate, int issueId, String fixTimeLine,
			Date date, String remarks) {
		Connection con = null;
		int count = 0;
		boolean flag = false;
		PreparedStatement ps = null;
		String str = "update SME_ISSUE_DESCRIPTION set PENDING_WITH=?,DEFECT_ID=?,STATUS=?,CLOSED_DATE=?,FIX_TIMELINE=?,SYS_UPDATE_DATE=?,REMARKS=? where ISSUE_ID=?";
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(str);
			ps.setString(1, pendingWith);
			ps.setString(2, defectId);
			ps.setString(3, status);
			ps.setString(4, closedDate);
			ps.setString(5, fixTimeLine);
			ps.setDate(6, sqlDate);
			ps.setString(7, remarks);
			ps.setInt(8, issueId);
			count = ps.executeUpdate();
			con.commit();
			flag = count > 0;
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return flag;
	}

	/*public ArrayList<IssueDescription> getIssueStatusWise(String status,
			int trackerId) {
		ArrayList<IssueDescription> issueDescList = new ArrayList<IssueDescription>();
		IssueMasterDAO issueMasterDAO = new IssueMasterDAO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<IssueMaster> issueMasters = issueMasterDAO.getAllTracker();
		UPSSTeamDAO teamDAO = new UPSSTeamDAO();
		ArrayList<UPSSTeam> upssTeamList = teamDAO.getAllTeams();
		String str = "select * from SME_ISSUE_DESCRIPTION where status =? and m_id=? order by SYS_UPDATE_DATE";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(str);
			ps.setString(1, status);
			ps.setInt(2, trackerId);
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("No issue with status :: " + status);
				return issueDescList;
			}
			while (rs.next()) {
				IssueDescription issueDescription = new IssueDescription();
				issueDescription.setIssueId(rs.getInt("ISSUE_ID"));
				for (UPSSTeam upssTeam : upssTeamList) {
					if (upssTeam.getTeamId() == rs.getInt("TEAM_ID")) {
						issueDescription.setUpssTeam(upssTeam);
						break;
					}
				}
				issueDescription.setIssueLogDate(rs.getDate("ISSUE_LOG_DATE"));
				issueDescription.setIssueName(rs.getString("ISSUE_NAME"));
				issueDescription
						.setIssueDesc(rs.getString("ISSUE_DESCRIPTION"));
				issueDescription.setStatus(rs.getString("STATUS"));
				issueDescription.setClosedDate(rs.getString("CLOSED_DATE"));
				issueDescription.setDefectId(rs.getString("DEFECT_ID"));
				issueDescription.setFixTimeLine(rs.getString("FIX_TIMELINE"));
				issueDescription.setPendingWith(rs.getString("PENDING_WITH"));
				issueDescription.setRemarks(rs.getString("REMARKS"));
				for (IssueMaster issueMaster : issueMasters) {
					if (issueMaster.getmId() == rs.getInt("M_ID")) {
						issueDescription.setIssueMaster(issueMaster);
						break;
					}
				}
				issueDescription
						.setSysUpdateDate(rs.getDate("SYS_UPDATE_DATE"));
				issueDescList.add(issueDescription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return issueDescList;
	}
*/
	public ArrayList<IssueDescription> fetchTeamAndStatusIssue(int teamId,
			String status, int trackerId) {
		ArrayList<IssueDescription> issueDescList = new ArrayList<IssueDescription>();
		IssueMasterDAO issueMasterDAO = new IssueMasterDAO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<IssueMaster> issueMasters = issueMasterDAO.getAllTracker();
		UPSSTeamDAO teamDAO = new UPSSTeamDAO();
		ArrayList<UPSSTeam> upssTeamList = teamDAO.getAllTeams();
		String query = "";
		if(teamId == 1){
			if(status.equals("ALL")){
				query = "select * from SME_ISSUE_DESCRIPTION where m_Id=? order by SYS_UPDATE_DATE desc";
			}else{
				query = "select * from SME_ISSUE_DESCRIPTION where status = '"+status+"' and m_Id=? order by SYS_UPDATE_DATE desc";	
			}
		}else {
			if(status.equals("ALL")){
				query = "select * from SME_ISSUE_DESCRIPTION where team_id = "+teamId+" and m_Id=? order by SYS_UPDATE_DATE desc";
			}else{
				query = "select * from SME_ISSUE_DESCRIPTION where team_id = "+teamId+" and status = '"+status+"' and m_Id=? order by SYS_UPDATE_DATE desc";	
			}
		}
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, trackerId);
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("No Open issue for team id :: " + teamId);
				return issueDescList;
			}
			while (rs.next()) {
				IssueDescription issueDescription = new IssueDescription();
				issueDescription.setIssueId(rs.getInt("ISSUE_ID"));
				for (UPSSTeam upssTeam : upssTeamList) {
					if (upssTeam.getTeamId() == rs.getInt("TEAM_ID")) {
						issueDescription.setUpssTeam(upssTeam);
						break;
					}
				}
				issueDescription.setIssueLogDate(rs.getDate("ISSUE_LOG_DATE"));
				issueDescription.setIssueName(rs.getString("ISSUE_NAME"));
				issueDescription
						.setIssueDesc(rs.getString("ISSUE_DESCRIPTION"));
				issueDescription.setStatus(rs.getString("STATUS"));
				issueDescription.setClosedDate(rs.getString("CLOSED_DATE"));
				issueDescription.setDefectId(rs.getString("DEFECT_ID"));
				issueDescription.setFixTimeLine(rs.getString("FIX_TIMELINE"));
				issueDescription.setPendingWith(rs.getString("PENDING_WITH"));
				issueDescription.setRemarks(rs.getString("REMARKS"));
				for (IssueMaster issueMaster : issueMasters) {
					if (issueMaster.getmId() == rs.getInt("M_ID")) {
						issueDescription.setIssueMaster(issueMaster);
						break;
					}
				}
				issueDescription
						.setSysUpdateDate(rs.getDate("SYS_UPDATE_DATE"));
				issueDescList.add(issueDescription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return issueDescList;
	}

	public int getSequence() {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String str = "select Issue_Tracker_Seq.nextval from dual";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(str);
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return count;
	}
	/*public ArrayList<IssueDescription> fetchAllIssues(int trackerId) {
		ArrayList<IssueDescription> issueDescList = new ArrayList<IssueDescription>();
		IssueMasterDAO issueMasterDAO = new IssueMasterDAO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<IssueMaster> issueMasters = issueMasterDAO.getAllTracker();
		UPSSTeamDAO teamDAO = new UPSSTeamDAO();
		ArrayList<UPSSTeam> upssTeamList = teamDAO.getAllTeams();
		String str = "select * from SME_ISSUE_DESCRIPTION where m_id=? order by SYS_UPDATE_DATE";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(str);
			ps.setInt(1, trackerId);
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("No Open issues");
				return issueDescList;
			}
			while (rs.next()) {
				IssueDescription issueDescription = new IssueDescription();
				issueDescription.setIssueId(rs.getInt("ISSUE_ID"));
				for (UPSSTeam upssTeam : upssTeamList) {
					if (upssTeam.getTeamId() == rs.getInt("TEAM_ID")) {
						issueDescription.setUpssTeam(upssTeam);
						break;
					}
				}
				issueDescription.setIssueLogDate(rs.getDate("ISSUE_LOG_DATE"));
				issueDescription.setIssueName(rs.getString("ISSUE_NAME"));
				issueDescription
						.setIssueDesc(rs.getString("ISSUE_DESCRIPTION"));
				issueDescription.setStatus(rs.getString("STATUS"));
				issueDescription.setClosedDate(rs.getString("CLOSED_DATE"));
				issueDescription.setDefectId(rs.getString("DEFECT_ID"));
				issueDescription.setFixTimeLine(rs.getString("FIX_TIMELINE"));
				issueDescription.setPendingWith(rs.getString("PENDING_WITH"));
				issueDescription.setRemarks(rs.getString("REMARKS"));
				for (IssueMaster issueMaster : issueMasters) {
					if (issueMaster.getmId() == rs.getInt("M_ID")) {
						issueDescription.setIssueMaster(issueMaster);
						break;
					}
				}
				issueDescription
						.setSysUpdateDate(rs.getDate("SYS_UPDATE_DATE"));
				issueDescList.add(issueDescription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return issueDescList;
	}*/
	
	public ArrayList<IssueDescription> getLatestUpdates(){
		ArrayList<IssueDescription> issueList = new ArrayList<IssueDescription>();
		IssueMasterDAO issueMasterDAO = new IssueMasterDAO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<IssueMaster> issueMasters = issueMasterDAO.getAllTracker();
		UPSSTeamDAO teamDAO = new UPSSTeamDAO();
		ArrayList<UPSSTeam> upssTeamList = teamDAO.getAllTeams();
		String str = "SELECT * FROM (SELECT ISSUE_ID,ISSUE_LOG_DATE,ISSUE_NAME,ISSUE_DESCRIPTION,STATUS,PENDING_WITH,DEFECT_ID,CLOSED_DATE,TEAM_ID,FIX_TIMELINE,M_ID,SYS_UPDATE_DATE,REMARKS "+
                      "from SME_ISSUE_DESCRIPTION order by SYS_UPDATE_DATE desc) where rownum <11";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(str);
			
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) {
				System.out.println("No Open issues");
				return issueList;
			}
			while (rs.next()) {
				IssueDescription issueDescription = new IssueDescription();
				issueDescription.setIssueId(rs.getInt("ISSUE_ID"));
				for (UPSSTeam upssTeam : upssTeamList) {
					if (upssTeam.getTeamId() == rs.getInt("TEAM_ID")) {
						issueDescription.setUpssTeam(upssTeam);
						break;
					}
				}
				issueDescription.setIssueLogDate(rs.getDate("ISSUE_LOG_DATE"));
				issueDescription.setIssueName(rs.getString("ISSUE_NAME"));
				issueDescription
						.setIssueDesc(rs.getString("ISSUE_DESCRIPTION"));
				issueDescription.setStatus(rs.getString("STATUS"));
				issueDescription.setClosedDate(rs.getString("CLOSED_DATE"));
				issueDescription.setDefectId(rs.getString("DEFECT_ID"));
				issueDescription.setFixTimeLine(rs.getString("FIX_TIMELINE"));
				issueDescription.setPendingWith(rs.getString("PENDING_WITH"));
				issueDescription.setRemarks(rs.getString("REMARKS"));
				for (IssueMaster issueMaster : issueMasters) {
					if (issueMaster.getmId() == rs.getInt("M_ID")) {
						issueDescription.setIssueMaster(issueMaster);
						break;
					}
				}
				issueDescription
						.setSysUpdateDate(rs.getDate("SYS_UPDATE_DATE"));
				issueList.add(issueDescription);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return issueList;
	}
	
	public ArrayList<SMEIssueSummary> getIssueSummary(int teamId, int trackerId){
		ArrayList<SMEIssueSummary> list = new ArrayList<SMEIssueSummary>();
		UPSSTeamDAO upssTeamDAO = new UPSSTeamDAO();
		ArrayList<UPSSTeam> teams = upssTeamDAO.getAllTeams();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String trackerName = "";
		String query = "";
		if(teamId == 1 || teamId == 6){
			query = "SELECT TEAM_ID,STATUS,COUNT(*) FROM SME_ISSUE_DESCRIPTION WHERE M_ID=? and status <> 'CLOSED' group by team_id,status";
		}else {
			query = "SELECT TEAM_ID,STATUS,COUNT(*) FROM SME_ISSUE_DESCRIPTION WHERE M_ID=? and team_id = "+teamId+" and status <> 'CLOSED' group by team_id,status";	
		}
		if(trackerId == 1){
			trackerName = "SME Issue Tracker";
		}else if(trackerId == 4){
		    trackerName = "Internal Tracker";
		}
		try{
			con=DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, trackerId);
			rs=ps.executeQuery();
			while(rs.next()){
				SMEIssueSummary issueSummary = new SMEIssueSummary();
				for(UPSSTeam team : teams){
					if(team.getTeamId() == rs.getInt(1)){
						issueSummary.setUpssTeam(team);
						break;
					}
				}
				issueSummary.setStatus(rs.getString(2));
				issueSummary.setIssueCount(rs.getInt(3));
				issueSummary.setTrackerType(trackerName);
				list.add(issueSummary);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return list;
	}
}
