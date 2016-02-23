package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.model.IssueMaster;

public class IssueMasterDAO {

	public IssueMaster getIssueType(int masterId){
		IssueMaster issueMaster = new IssueMaster();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String str="select * from SME_ISSUE_MASTER where m_id=?";
		try{
			con=DBUtil.getConnection();
			ps=con.prepareStatement(str);
			ps.setInt(1, masterId);
			rs=ps.executeQuery();
			while(rs.next()){
				issueMaster.setmId(rs.getInt("M_ID"));
				issueMaster.setSysCreationDate(rs.getDate("SYS_CREATION_DATE"));
				issueMaster.setIssueType(rs.getString("ISSUE_TYPE"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return issueMaster;
	}
	public ArrayList<IssueMaster> getAllTracker(){
		ArrayList<IssueMaster> issueMasterList = new ArrayList<IssueMaster>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String str="select * from SME_ISSUE_MASTER";
		try{
			con=DBUtil.getConnection();
			ps=con.prepareStatement(str);
			rs=ps.executeQuery();
			while(rs.next()){
				IssueMaster issueMaster = new IssueMaster();
				issueMaster.setmId(rs.getInt("M_ID"));
				issueMaster.setSysCreationDate(rs.getDate("SYS_CREATION_DATE"));
				issueMaster.setIssueType(rs.getString("ISSUE_TYPE"));
				issueMasterList.add(issueMaster);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return issueMasterList;
	}
}
