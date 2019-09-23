package com.geeks.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.geeks.bean.CategoryBean;
import com.geeks.daoimp.CategoryDaoImp;
import com.geeks.daoimp.UserTypeDaoImp;
import com.geeks.util.Encryption;
import com.sun.jmx.snmp.Timestamp;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     java.sql.Timestamp time=new java.sql.Timestamp(System.currentTimeMillis());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 if(request.getParameter("deleteId")!=null) {
			 CategoryBean category=new CategoryBean();
			category.setCategoryId(  Encryption.decrypt( request.getParameter("deleteId")));
			category.setModifiedBy(LoginServlet.userBean.getUserName());
			category.setModifiedDate(time.toString());
			 int row=new CategoryDaoImp().deleteCategory(category);
			 response.sendRedirect("category.jsp");
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("updateId")==null) {
		try {
			CategoryBean category=new CategoryBean();
			 category.setName(request.getParameter("name"));
			
			 category.setParentId(Integer.parseInt(request.getParameter("parent")));
			 
			 category.setCreatedBy(LoginServlet.userBean.getUserName());
			 category.setModifiedBy(LoginServlet.userBean.getUserName());
			 new CategoryDaoImp().addCategory(category);
			 response.sendRedirect("category.jsp");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		else {
			CategoryBean category=new CategoryBean();
			category.setCategoryId(Encryption.decrypt( request.getParameter("updateId")));
			category.setName(request.getParameter("name"));
			category.setParentId(Integer.parseInt(request.getParameter("parent")));
			category.setModifiedBy(LoginServlet.userBean.getUserName());
			category.setModifiedDate(time.toString());
			new CategoryDaoImp().updateCategory(category);
			response.sendRedirect("category.jsp");
			
		}
	}

}
