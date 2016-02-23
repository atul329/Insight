package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.model.IssueDescription;
import com.model.IssueDetail;
import com.model.Login;

public class IssueDetailDAO {

	public ArrayList<IssueDetail> fetchIssue(int issueId, ArrayList<Login> loginList){
		ArrayList<IssueDetail> issueDetailList = new ArrayList<IssueDetail>();
		IssueDescriptionDAO issueDescriptionDAO = new IssueDescriptionDAO();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String str = "select * from SME_ISSUE_DETAIL where issue_id = ? order by 1";
		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(str);
			ps.setInt(1, issueId);
			rs =ps.executeQuery();
			if (!rs.isBeforeFirst()){
				System.out.println("No Update for issue with issue id :: "+issueId);
				return issueDetailList;
			}
			IssueDescription issueDescription = issueDescriptionDAO.fetchIssueDescription(issueId);
			while(rs.next()){
				IssueDetail issueDetail = new IssueDetail();
				issueDetail.setSysUpdateDate(rs.getDate("SYS_UPDATE_DATE"));
				issueDetail.setRemarks(rs.getString("REMARKS"));
				issueDetail.setIssueDescription(issueDescription);
				issueDetail.setPendingWith(rs.getString("PENDING_WITH"));
				for(Login login : loginList){
					if(login.getLoginId() == rs.getInt("UPDATED_BY")){
						issueDetail.setLogin(login);	
						break;
					}
				}
				issueDetail.setIssueDetailId(rs.getInt("ISSUE_DETAIL_ID"));
				issueDetail.setFrequency(rs.getString("FREQUENCY"));
	    		issueDetail.setDeployed(rs.getString("DEPLOYED"));
	    		issueDetail.setCount(rs.getString("OCCURENCE"));
	    		issueDetail.setTicket(rs.getString("TICKET"));
	    		issueDetail.setPriority(rs.getString("PRIORITY"));
	    		issueDetail.setEffort(rs.getString("EFFORT"));
				issueDetailList.add(issueDetail);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return issueDetailList;
	}
	
	public boolean addUpdates(IssueDetail issueDetail){
		int count = 0;
		boolean flag = false;
		Connection con = null;
		PreparedStatement ps = null;
		String str = "insert into SME_ISSUE_DETAIL (SYS_UPDATE_DATE,ISSUE_ID,REMARKS,PENDING_WITH,UPDATED_BY,ISSUE_DETAIL_ID) values(?,?,?,?,?,ISSUE_DETAIL_SEQ.nextval)";
		try{
			con=DBUtil.getConnection();
			ps=con.prepareStatement(str);
			ps.setDate(1, new java.sql.Date(issueDetail.getSysUpdateDate().getTime()));
			ps.setInt(2, issueDetail.getIssueDescription().getIssueId());
			ps.setString(3, issueDetail.getRemarks());
			ps.setString(4, issueDetail.getPendingWith());
			ps.setInt(5, issueDetail.getLogin().getLoginId());
			count = ps.executeUpdate();		
			con.commit();
			flag = count > 0;
		}catch (Exception e) {
			e.printStackTrace();	
		} finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return flag;
	}
	public boolean insertIssueDetail(IssueDetail issueDetail){
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;
		boolean flag = false; 
		String str = "insert into SME_ISSUE_DETAIL (SYS_UPDATE_DATE,ISSUE_ID,REMARKS,PENDING_WITH,UPDATED_BY,FREQUENCY,DEPLOYED,OCCURENCE,TICKET,PRIORITY,EFFORT,ISSUE_DETAIL_ID) values(?,?,?,?,?,?,?,?,?,?,?,ISSUE_DETAIL_SEQ.nextval)";
		try{
			con=DBUtil.getConnection();
			ps=con.prepareStatement(str);
			ps.setDate(1, new java.sql.Date(issueDetail.getSysUpdateDate().getTime()));
			ps.setInt(2, issueDetail.getIssueDescription().getIssueId());
			ps.setString(3, issueDetail.getRemarks());
			ps.setString(4, issueDetail.getPendingWith());
			ps.setInt(5, issueDetail.getLogin().getLoginId());
			ps.setString(6, issueDetail.getFrequency());
			ps.setString(7, issueDetail.getDeployed());
			ps.setString(8, issueDetail.getCount());
			ps.setString(9, issueDetail.getTicket());
			ps.setString(10, issueDetail.getPriority());
			ps.setString(11, issueDetail.getEffort());
			count = ps.executeUpdate();
			con.commit();
			flag = count > 0;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		
		return flag;
	}
	
	public ArrayList<IssueDetail> fetchAllForWA(){
		ArrayList<IssueDetail> issueDetailList = new ArrayList<IssueDetail>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		IssueDescriptionDAO issueDescriptionDAO = new IssueDescriptionDAO();
		LoginDAO loginDAO = new LoginDAO();
		String str = "select a.* from SME_ISSUE_DETAIL a, SME_ISSUE_DESCRIPTION B where a.ISSUE_ID = B.ISSUE_ID and B.M_ID = 2 and a.issue_detail_id in (select max(issue_detail_id) from SME_ISSUE_DETAIL C where a.ISSUE_ID = C.ISSUE_ID)";
	    try{
	    	con=DBUtil.getConnection();
	    	ps=con.prepareStatement(str);
	    	rs=ps.executeQuery();
	    	while(rs.next()){
	    		IssueDetail issueDetail = new IssueDetail();
	    		issueDetail.setSysUpdateDate(rs.getDate("SYS_UPDATE_DATE"));
	    		issueDetail.setIssueDescription(issueDescriptionDAO.fetchIssueDescription(rs.getInt("ISSUE_ID")));
	    		issueDetail.setRemarks(rs.getString("REMARKS"));
	    		issueDetail.setPendingWith(rs.getString("PENDING_WITH"));
	    		issueDetail.setLogin(loginDAO.fetchUser(rs.getInt("UPDATED_BY")));
	    		issueDetail.setFrequency(rs.getString("FREQUENCY"));
	    		issueDetail.setDeployed(rs.getString("DEPLOYED"));
	    		issueDetail.setCount(rs.getString("OCCURENCE"));
	    		issueDetail.setTicket(rs.getString("TICKET"));
	    		issueDetail.setPriority(rs.getString("PRIORITY"));
	    		issueDetail.setEffort(rs.getString("EFFORT"));
	    		issueDetail.setIssueDetailId(rs.getInt("ISSUE_DETAIL_ID"));
	    		issueDetailList.add(issueDetail);
	    	}
	    	
	    }catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return issueDetailList;
	}
	
	public IssueDetail fetchLatestRecord(int issueId){
		IssueDetail issueDetail = new IssueDetail();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		IssueDescriptionDAO issueDescriptionDAO = new IssueDescriptionDAO();
		LoginDAO loginDAO = new LoginDAO();
		String str="select a.* from SME_ISSUE_DETAIL a where a.ISSUE_DETAIL_ID = (select max(b.issue_Detail_id) from SME_ISSUE_DETAIL b where b.issue_id = ?)";
	    try{
	    	con=DBUtil.getConnection();
	    	ps=con.prepareStatement(str);
	    	ps.setInt(1, issueId);
	    	rs=ps.executeQuery();
	    	while(rs.next()){
	    		issueDetail.setSysUpdateDate(rs.getDate("SYS_UPDATE_DATE"));
	    		issueDetail.setIssueDescription(issueDescriptionDAO.fetchIssueDescription(rs.getInt("ISSUE_ID")));
	    		issueDetail.setRemarks(rs.getString("REMARKS"));
	    		issueDetail.setPendingWith(rs.getString("PENDING_WITH"));
	    		issueDetail.setLogin(loginDAO.fetchUser(rs.getInt("UPDATED_BY")));
	    		issueDetail.setFrequency(rs.getString("FREQUENCY"));
	    		issueDetail.setDeployed(rs.getString("DEPLOYED"));
	    		issueDetail.setCount(rs.getString("OCCURENCE"));
	    		issueDetail.setTicket(rs.getString("TICKET"));
	    		issueDetail.setPriority(rs.getString("PRIORITY"));
	    		issueDetail.setEffort(rs.getString("EFFORT"));
	    		issueDetail.setIssueDetailId(rs.getInt("ISSUE_DETAIL_ID"));
	    	}
	    }catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
	    return issueDetail;
	}
/*	public IssueDetail fetchIssueDetail(IssueDescription issueDecription){
		IssueDetail issueDetail = new IssueDetail();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String str = "select * from SME_ISSUE_DETAIL where issue_id = ?";
		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement(str);
			ps.setInt(1, issueDecription.getIssueId());
			rs =ps.executeQuery();
			if (!rs.isBeforeFirst()){
				System.out.println("No Update for issue with issue id :: "+issueDecription.getIssueId());
				return issueDetail;
			}
			while(rs.next()){
				issueDetail.setSysUpdateDate(rs.getDate("SYS_UPDATE_DATE"));
				issueDetail.setRemarks(rs.getString("REMARKS"));
				issueDetail.setIssueDescription(issueDecription);
				issueDetail.setPendingWith(rs.getString("PENDING_WITH"));
				//issueDetail.get
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		
		return issueDetail;
	}*/
}