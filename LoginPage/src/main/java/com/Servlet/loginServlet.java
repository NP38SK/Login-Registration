package com.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.UserDao;
import com.DB.DBConnect;
import com.entites.User;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDao  dao = new UserDao(DBConnect.getConn());
		User user = dao.getLogin(email, password);
		
		
		if(user!=null) {
			/*
			 * PrintWriter out = response.getWriter(); out.println("Login Succefully..");
			 */
			
			HttpSession session  = request.getSession();
			session.setAttribute("user-obj", user);
			response.sendRedirect("home.jsp");
			
		}
		else {
			/*
			 * PrintWriter out = response.getWriter();
			 * out.println("Invalid email & password..");
			 */
			
			HttpSession session  = request.getSession();
			session.setAttribute("error-msg", "Invalid email & password...");
			response.sendRedirect("login.jsp");
		}

	}

}
