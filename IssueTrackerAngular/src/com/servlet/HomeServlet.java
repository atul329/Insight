package com.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.IssueDescriptionDAO;
import com.DAO.IssueDetailDAO;
import com.DAO.IssueMasterDAO;
import com.DAO.LoginDAO;
import com.DAO.UPSSTeamDAO;
import com.google.gson.Gson;
import com.model.IssueDescription;
import com.model.IssueDetail;
import com.model.IssueMaster;
import com.model.Login;
import com.model.SMEIssueSummary;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		IssueDescriptionDAO issueDescriptionDAO = new IssueDescriptionDAO();
		ArrayList<IssueDescription> issueDescriptions = new ArrayList<IssueDescription>();
		IssueDetailDAO issueDetailDAO = new IssueDetailDAO();
		LoginDAO loginDAO = new LoginDAO();
		String action = request.getParameter("action");
		if(action.equalsIgnoreCase("getAllData")){
			int trackerId = Integer.parseInt(request.getParameter("trackerId").trim());
			issueDescriptions = issueDescriptionDAO.getAllIssues(trackerId);
			String json = new Gson().toJson(issueDescriptions);
			System.out.println("Value of JSON :: "+json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
		else if(action.equalsIgnoreCase("viewDetails")){
		   ArrayList<Login> loginList = new ArrayList<Login>();
		   if(session.getAttribute("AllUsers") == null || session.getAttribute("AllUsers") !=""){
			   loginList = loginDAO.getAllUser();
		   }else{
			   loginList = ((ArrayList<Login>) session.getAttribute("AllUsers"));
		   }
		   int issueId = Integer.parseInt(request.getParameter("issueId"));  
		   ArrayList<IssueDetail> issueDetailList = issueDetailDAO.fetchIssue(issueId, loginList);
		   String json = new Gson().toJson(issueDetailList);
		   System.out.println("Value of JSON :: "+json);
		   response.setContentType("application/json");
		   response.setCharacterEncoding("UTF-8");
		   response.getWriter().write(json);
		}
		else if(action.equalsIgnoreCase("getSMETrackerSummary")){
			int teamId = Integer.parseInt(request.getParameter("teamId").trim());
			int trackerId = Integer.parseInt(request.getParameter("trackerId").trim());
			ArrayList<SMEIssueSummary> arrayList = issueDescriptionDAO.getIssueSummary(teamId, trackerId);
			String json = new Gson().toJson(arrayList); // Need to Add proper Issue summary
			System.out.println("Value of JSON :: "+json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
	}

	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		IssueDescriptionDAO issueDescriptionDAO = new IssueDescriptionDAO();
		IssueMasterDAO issueMasterDAO = new IssueMasterDAO();
		UPSSTeamDAO upssTeamDAO = new UPSSTeamDAO();
		LoginDAO loginDAO = new LoginDAO();
		IssueDetailDAO issueDetailDAO = new IssueDetailDAO();
		System.out.println("Action in Home POST :: "+action);
		if(action.equalsIgnoreCase("addIssue")){
			String logDate = request.getParameter("logDate").substring(0,24);
			String issueName = request.getParameter("issueName");
			String issueDesc = request.getParameter("issueDesc");
			String status = request.getParameter("status");
			String reportedBy = request.getParameter("reportedBy");
			String pendingWith = request.getParameter("pendingWith");
			String remarks = request.getParameter("remarks");
			String defectId = request.getParameter("defectId");
			String username = request.getParameter("username");
			System.out.println("username is :: "+username);
			int trackerId = Integer.parseInt(request.getParameter("tracker").trim());
			System.out.println("Tracker Id :: "+request.getParameter("tracker"));
			SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd yyyy hh:mm:ss");
			Date issueDate = null;
		    try {
				issueDate = sdf.parse(logDate);
				System.out.println(issueDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			IssueDescription issueDescription = new IssueDescription();
			issueDescription.setIssueLogDate(issueDate);
			issueDescription.setIssueId(issueDescriptionDAO.getSequence());
			issueDescription.setIssueName(issueName);
			issueDescription.setIssueDesc(issueDesc);
			issueDescription.setIssueMaster(issueMasterDAO.getIssueType(trackerId));
			issueDescription.setPendingWith(pendingWith);
			issueDescription.setStatus(status);
			issueDescription.setUpssTeam(upssTeamDAO.getTeam(Integer.parseInt(reportedBy)));
			issueDescription.setRemarks(remarks);
			issueDescription.setDefectId(defectId);
			issueDescription.setFixTimeLine("NA");
			issueDescription.setClosedDate("NA");
			issueDescription.setSysUpdateDate(issueDate);
			
			IssueDetail issueDetail = new IssueDetail();
			issueDetail.setSysUpdateDate(issueDate);
			issueDetail.setIssueDescription(issueDescription);
			issueDetail.setLogin(loginDAO.getUserDetails(username.trim()));
			issueDetail.setPendingWith(pendingWith);
			issueDetail.setRemarks(remarks);
			issueDetail.setSysUpdateDate(issueDate);
			
			String priority = "";
			String frequency = "";
			String ticket = "";
			String deployed = "";
			String occurrence = "";
			String effort = "";
			if(trackerId == 2){                   //tracker Id is 2 for workAround Tracker
				System.out.println("Tracker Id :: "+trackerId);
				priority = request.getParameter("priority");
				frequency = request.getParameter("frequency");
				ticket = request.getParameter("ticket");
				deployed = request.getParameter("deployed");
				occurrence = request.getParameter("occurrence");
				effort = request.getParameter("efforts");
				issueDetail.setEffort(effort);
				issueDetail.setPriority(priority);
				issueDetail.setFrequency(frequency);
				issueDetail.setTicket(ticket);
				issueDetail.setCount(occurrence);
				issueDetail.setDeployed(deployed);			
			}
			boolean flag = false;
			flag = issueDescriptionDAO.addIssue(issueDescription);
			flag =  issueDetailDAO.insertIssueDetail(issueDetail);
			String json = new Gson().toJson(flag);
			System.out.println("Add issue home servlet :: "+json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);		
		}
	
		else if(action.equalsIgnoreCase("updateIssue")){
			String logDate = request.getParameter("logDate").substring(0,24);
			String status = request.getParameter("status");
			String pendingWith = request.getParameter("pendingWith");
			String remarks = request.getParameter("remarks");
			int issueId = Integer.parseInt(request.getParameter("issueId"));
			int trackerId = Integer.parseInt(request.getParameter("trackerId"));
			String defectId = request.getParameter("defectId");
			String fixTimeLine = request.getParameter("fixTimeLine");
			String closingDate = request.getParameter("closingDate");
			String username = request.getParameter("username");
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd yyyy hh:mm:ss");
		    try {
		    	date = sdf.parse(logDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            boolean flag = false;
            flag = issueDescriptionDAO.updateIssueDesciption(pendingWith, defectId, status, closingDate, issueId, fixTimeLine, date, remarks);
            IssueDetail issueDetail = new IssueDetail();
            issueDetail.setSysUpdateDate(date);
            issueDetail.setIssueDescription(issueDescriptionDAO.fetchIssueDescription(issueId));
            issueDetail.setPendingWith(pendingWith);
            issueDetail.setRemarks(remarks);
            issueDetail.setLogin(loginDAO.getUserDetails(username));
            flag = issueDetailDAO.addUpdates(issueDetail);
            String json = new Gson().toJson(flag);
			System.out.println("Update issue home servlet :: "+json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
	}

}
