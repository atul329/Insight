package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.LoginDAO;
import com.google.gson.Gson;
import com.logic.DataLogic;
import com.model.Login;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Inside Login Servlet");
		String username = request.getParameter("username");
		System.out.println("username is :: " + username);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		System.out.println("Inside Login Servlet Post method");
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("login")) {
			LoginDAO loginDAO = new LoginDAO();
			ArrayList<Login> loginList = loginDAO.getAllUser();
			session.setAttribute("AllUsers", loginList);
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Login login = loginDAO.getUser(username,
					DataLogic.convertToHex(password));
			String json = new Gson().toJson(login);
			System.out.println("Login servlet user details :: " + json);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);
		} else if (action.equalsIgnoreCase("changePassword")) {
			LoginDAO loginDAO = new LoginDAO();
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			int loginId = Integer.parseInt(request.getParameter("loginId").trim());
			boolean flag = false;
			boolean isChanged = false;
			flag = loginDAO.checkUser(loginId, DataLogic.convertToHex(oldPassword));
			if(flag){
				isChanged = loginDAO.changePassword(DataLogic.convertToHex(newPassword), loginId);
				String json = new Gson().toJson(isChanged);
				System.out.println("Login servlet user details :: " + json);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
				
			}else{
				String json = new Gson().toJson(flag);
				System.out.println("Login servlet user details :: " + json);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			}

		}
	}

}
