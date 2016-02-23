package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.IssueDescriptionDAO;
import com.DAO.IssueDetailDAO;
import com.google.gson.Gson;
import com.model.IssueDescription;

/**
 * Servlet implementation class FilterServlet
 */
public class FilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FilterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		IssueDescriptionDAO issueDescriptionDAO = new IssueDescriptionDAO();
		if(action.equalsIgnoreCase("getTeamIssue")){
			int teamId = Integer.parseInt(request.getParameter("team"));
			String status = request.getParameter("status");
			int trackerId = Integer.parseInt(request.getParameter("trackerId"));
			System.out.println("Team is :: "+teamId+ " status is :: "+status);
			ArrayList<IssueDescription> issueDescriptions = issueDescriptionDAO.fetchTeamAndStatusIssue(teamId, status, trackerId);
			String json = new Gson().toJson(issueDescriptions);
			System.out.println("Value of JSON :: "+json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
		else if(action.equalsIgnoreCase("getLatestIssues")){
			ArrayList<IssueDescription> issueDescriptions = issueDescriptionDAO.getLatestUpdates();
			String json = new Gson().toJson(issueDescriptions);
			System.out.println("Value of JSON :: "+json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
