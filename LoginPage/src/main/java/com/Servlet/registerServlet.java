package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UserDao;
import com.DB.DBConnect;
import com.entites.User;

@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User us = new User();
		us.setName(name);
		us.setEmail(email);
		us.setPassword(password);
		UserDao dao = new UserDao(DBConnect.getConn());
		boolean f = dao.UserRegister(us);
		if(f) {
//			PrintWriter out = response.getWriter();
//			out.println("Data inserted successfully");
			HttpSession session = request.getSession();
			session.setAttribute("reg-msg", "Registration Successfully......");
			response.sendRedirect("register.jsp");
		}else {
			/*
			 * PrintWriter out = response.getWriter(); out.println("Data not inserted");
			 */
			HttpSession session = request.getSession();
			session.setAttribute("error-msg", "Registration failed......");
			response.sendRedirect("register.jsp");
		}
	}

}
