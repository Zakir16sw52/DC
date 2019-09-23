package com.geeks.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.geeks.bean.QuestionBean;
import com.geeks.bean.UserBean;
import com.geeks.bean.UserTypeBean;
import com.geeks.daoimp.UserDaoImp;
import com.geeks.util.Encryption;

/**	
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private File file;
	private String filePath = "";
	private UserBean ub = new UserBean();
	private UserTypeBean userType = new UserTypeBean();
	private QuestionBean question = new QuestionBean();
	private Timestamp time = new Timestamp(System.currentTimeMillis());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
	}

	public void init() {
		// Get the file location where it would be stored.
		filePath = getServletContext().getInitParameter("file-upload");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("deleteId") != null) {
			ub.setModifiedBy(LoginServlet.userBean.getUserName());
			ub.setModifiedDate(time.toString());
			ub.setUserId(Encryption.decrypt(request.getParameter("deleteId")));
			if (new UserDaoImp().deleteUser(ub) == 1) {
				response.sendRedirect("index.jsp");
			} else {
				System.out.println("Not Deleted");
			}
		}
		if (request.getParameter("updateId") != null) {
			System.out.println("User Created");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("updateId") == null && request.getParameter("deleteId") == null) {
			DiskFileItemFactory factory = new DiskFileItemFactory();

			PrintWriter out = response.getWriter();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request to get file items.
				List<FileItem> fileItems = upload.parseRequest(request);

				// Process the uploaded file items
				Iterator<FileItem> i = fileItems.iterator();

				String path = "";
				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					if (!fi.isFormField()) {
						// Get the uploaded file parameters
						String fieldName = fi.getFieldName();
						String fileName = fi.getName();
						String contentType = fi.getContentType();
						boolean isInMemory = fi.isInMemory();
						long sizeInBytes = fi.getSize();
						out.print("filename=" + fileName);
						// Write the file
						
						if (fileName.lastIndexOf("\\") >= 0) {
							file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
							path = fileName.substring(fileName.lastIndexOf("\\"));
							out.println("If Filename: " + path + "<br>");
						} else {
							file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
							path = fileName.substring(fileName.lastIndexOf("\\") + 1);
							out.println("else Filename: " + path + "<br>");
						}
						fi.write(file);
						out.println("Uploaded Filename: " + fileName + "<br>");
						out.println("Uploaded Filename: " + path + "<br>");
						
					} else {

						if (fi.getFieldName().equals("username")) {
							ub.setUserName(fi.getString());
						}
						if (fi.getFieldName().equals("password")) {
							ub.setPassword(fi.getString());
						}
						if (fi.getFieldName().equals("usertype")) {
							userType.setUserTypeId(Integer.parseInt(fi.getString()));
						}

						if (fi.getFieldName().equals("name")) {
							ub.setName(fi.getString());
						}

						if (fi.getFieldName().equals("address")) {
							ub.setAddress(fi.getString());
						}
						if (fi.getFieldName().equals("number")) {
							ub.setContact(fi.getString());
						}
						if (fi.getFieldName().equals("question")) {
							question.setQuestionId(Integer.parseInt(fi.getString()));
						}

						if (fi.getFieldName().equals("answer")) {
							ub.setAnswer(fi.getString());
						}
					}
				}
				UserDaoImp userImpl = new UserDaoImp();
				ub.setImage(path);
				ub.setUserType(userType);
				ub.setQuestion(question);
				ub.setCreatedBy(LoginServlet.userBean.getUserName());
				ub.setModifiedBy(LoginServlet.userBean.getUserName());
				userImpl.addUser(ub);
				response.sendRedirect("index.jsp");
			} catch (Exception ex) {
				System.out.println(ex);
			}
		} else if (request.getParameter("updateId") != null) {
			DiskFileItemFactory factory = new DiskFileItemFactory();

			PrintWriter out = response.getWriter();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				// Parse the request to get file items.
				List<FileItem> fileItems = upload.parseRequest(request);

				// Process the uploaded file items
				Iterator<FileItem> i = fileItems.iterator();

				String path = "";
				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					if (!fi.isFormField()) {
						// Get the uploaded file parameters
						String fieldName = fi.getFieldName();
						String fileName = fi.getName();
						String contentType = fi.getContentType();
						boolean isInMemory = fi.isInMemory();
						long sizeInBytes = fi.getSize();
						out.println("filename=" + fileName);
						// Write the file
						if(!fileName.equals("")) {
						if (fileName.lastIndexOf("\\") >= 0) {
							file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
							path = fileName.substring(fileName.lastIndexOf("\\"));
							out.println("If Filename: " + path + "<br>");
						} else {
							file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
							path = fileName.substring(fileName.lastIndexOf("\\") + 1);
							out.println("else Filename: " + path + "<br>");
						}
						fi.write(file);
						out.println("Uploaded Filename: " + fileName + "<br>");
						out.println("Uploaded Filename: " + path + "<br>");
					}else {
						System.out.println("else");
						path=LoginServlet.userBean.getImage();
					}
					} else {

						if (fi.getFieldName().equals("username")) {
							ub.setUserName(fi.getString());
						}
						if (fi.getFieldName().equals("password")) {
							ub.setPassword(fi.getString());
						}
						if (fi.getFieldName().equals("usertype")) {
							userType.setUserTypeId(Integer.parseInt(fi.getString()));
						}

						if (fi.getFieldName().equals("name")) {
							ub.setName(fi.getString());
						}

						if (fi.getFieldName().equals("address")) {
							ub.setAddress(fi.getString());
						}
						if (fi.getFieldName().equals("number")) {
							ub.setContact(fi.getString());
						}
						if (fi.getFieldName().equals("question")) {
							question.setQuestionId(Integer.parseInt(fi.getString()));
						}

						if (fi.getFieldName().equals("answer")) {
							ub.setAnswer(fi.getString());
						}
					}
				}
				UserDaoImp userImpl = new UserDaoImp();
				ub.setUserId( Encryption.decrypt(request.getParameter("updateId")));
				ub.setImage(path);
				ub.setUserType(userType);
				ub.setQuestion(question);
				ub.setModifiedBy(LoginServlet.userBean.getUserName());
				ub.setModifiedDate(time.toString());
				System.out.println("Update");
				userImpl.updateUser(ub); 
				response.sendRedirect("index.jsp");

			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}

}
