package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.model.UPSSTeam;

public class UPSSTeamDAO {

	public ArrayList<UPSSTeam> getAllTeams(){
		ArrayList<UPSSTeam> upssTeamList = new ArrayList<UPSSTeam>();	
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String str="select * from SME_UPSS_TEAM_DESCRIPTION";
		try{
			con=DBUtil.getConnection();
			ps=con.prepareStatement(str);
			rs=ps.executeQuery();
			while(rs.next()){
			 UPSSTeam upssTeam = new UPSSTeam();
			 upssTeam.setTeamId(rs.getInt("TEAM_ID"));
			 upssTeam.setTeamDesc(rs.getString("DESCRIPTION"));
			 upssTeam.setTeamName(rs.getString("TEAM_NAME"));
			 upssTeam.setLead(rs.getString("LEAD"));
			 upssTeam.setBackUpLead(rs.getString("BACKUP_LEAD"));
			 upssTeam.setContactNo(rs.getString("CONTACT_NO"));
			 upssTeamList.add(upssTeam);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return upssTeamList;
	}
	
	public UPSSTeam getTeam(int teamId){
		UPSSTeam upssTeam = new UPSSTeam();	
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String str="select * from SME_UPSS_TEAM_DESCRIPTION where TEAM_ID=?";
		try{
			con=DBUtil.getConnection();
			ps=con.prepareStatement(str);
			ps.setInt(1, teamId);
			rs=ps.executeQuery();
			while(rs.next()){ 
			 upssTeam.setTeamId(rs.getInt("TEAM_ID"));
			 upssTeam.setTeamDesc(rs.getString("DESCRIPTION"));
			 upssTeam.setTeamName(rs.getString("TEAM_NAME"));
			 upssTeam.setLead(rs.getString("LEAD"));
			 upssTeam.setBackUpLead(rs.getString("BACKUP_LEAD"));
			 upssTeam.setContactNo(rs.getString("CONTACT_NO"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeConnection(con);
		}
		return upssTeam;
	}
}
