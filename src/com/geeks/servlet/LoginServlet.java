  	package com.geeks.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.geeks.bean.UserBean;
import com.geeks.daoimp.UserDaoImp;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PrintWriter out;
    public static  UserBean userBean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("username")!=null) {
			response.sendRedirect("index.jsp");
		}
	
		UserBean bean=new UserBean();
		
		 bean.setUserName(request.getParameter("username"));
		 bean.setPassword(request.getParameter("password"));
		 System.out.println("Name:"+bean.getUserName());
		 System.out.println("Name:"+bean.getPassword());
		 System.out.println("Created");
		 if(!(bean.getUserName().equals("")&&bean.getPassword().equals(""))) {
			 UserBean login=new UserDaoImp().checkUser(bean);
			 if(login!=null) {
				 HttpSession session=request.getSession();
				 session.setAttribute("username", bean.getUserName());
				 session.setAttribute("image",login.getImage());
				 userBean=login;
				 response.sendRedirect("index.jsp");
			 }
		 }
		 else {
			 System.out.println("Did not Found");
		 }
		 
	}

}
