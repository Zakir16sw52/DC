package com.geeks.servlet;


import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

 
import com.geeks.bean.UserTypeBean;
import com.geeks.daoimp.UserTypeDaoImp;
import com.geeks.util.Encryption;

/**
 * Servlet implementation class UserTypeServlet
 */
@WebServlet("/UserTypeServlet")
public class UserTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserTypeBean userType=new UserTypeBean();
	Timestamp time=new Timestamp(System.currentTimeMillis());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  if(request.getParameter("deleteId")!=null) {
			 System.out.println("LoginServletCreatedBy="+LoginServlet.userBean.getUserName());
			 userType.setModifiedBy(LoginServlet.userBean.getUserName());
			 System.out.println("Modified By="+userType.getModifiedBy());
			 userType.setModifiedDate(time.toString());
			 userType.setUserTypeId(Encryption.decrypt(request.getParameter("deleteId")));
			if(new UserTypeDaoImp().deteteUserType(userType)==1) {
				response.sendRedirect("usertype.jsp");
			}
			else {
				System.out.println("Not Deleted");
			}
		  }
		}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("updateId")==null&&request.getParameter("deleteId")==null) {
			try {
			 userType.setName(request.getParameter("name"));
			 userType.setCreatedBy(LoginServlet.userBean.getUserName());
			 userType.setModifiedBy(LoginServlet.userBean.getUserName());
			 new UserTypeDaoImp().addUserType(userType);
			response.sendRedirect("usertype.jsp");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if(request.getParameter("updateId")!=null) {
			  
			 userType.setUserTypeId(Encryption.decrypt(request.getParameter("updateId")));
			 userType.setName(request.getParameter("name"));
			 userType.setModifiedDate(time.toString());
			 userType.setModifiedBy(LoginServlet.userBean.getUserName());
			 new UserTypeDaoImp().updateUserType(userType);
			response.sendRedirect("usertype.jsp");
		}
		

	}

}
