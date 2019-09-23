<%@page import="com.geeks.util.Encryption"%>
<%@page import="com.geeks.daoimp.QuestionDaoImp"%>
<%@page import="com.geeks.bean.QuestionBean"%>
<%@page import="com.geeks.bean.UserBean"%>
<%@page import="com.geeks.daoimp.UserDaoImp"%>
<%@page import="com.geeks.daoimp.UserTypeDaoImp"%>
<%@page import="com.geeks.bean.UserTypeBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.geeks.servlet.LoginServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ include file="header.jsp" %>
 
 <%
 	 response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
 	response.setHeader("pargma", "no-cache");
 	response.setHeader("Expires", "0");
 	if(request.getSession().getAttribute("username")==null){
 		 
 		RequestDispatcher dis=request.getRequestDispatcher("login.jsp");
 		dis.forward(request, response);
 	}
 	
 	filePath = getServletContext().getInitParameter("file-upload");
 %>
 <%! String filePath;%>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
      <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
        <a class="navbar-brand brand-logo" href="index.html"><img src="http://www.urbanui.com/boardsterui/template/images/logo-white.svg" alt="logo"/></a>
        <a class="navbar-brand brand-logo-mini" href="index.html"><img src="http://www.urbanui.com/boardsterui/template/images/logo-mini.svg" alt="logo"/></a>
      </div>
      <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end justify-content-lg-start">
        <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
          <span class="mdi mdi-menu"></span>
        </button>
        <ul class="navbar-nav mr-lg-2">
          <li class="nav-item nav-search d-none d-lg-block">
            <div class="input-group">
              <div class="input-group-prepend">
                <span class="input-group-text" id="search">
                  <i class="mdi mdi-magnify"></i>
                </span>
              </div>
              <input type="text" class="form-control" placeholder="search" aria-label="search" aria-describedby="search">
            </div>
          </li>
        </ul>
        <ul class="navbar-nav navbar-nav-right">
           
          <li class="nav-item nav-profile dropdown">
            <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown">
              <img src="${pageContext.request.contextPath}/images/<%= LoginServlet.userBean.getImage()%>" alt="profile"/>
              <span class="nav-profile-name"> <%=LoginServlet.userBean.getName()%></span>
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
            
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="LogoutServlet">
                <i class="mdi mdi-logout text-primary"></i>
                Logout
              </a>
            </div>
          </li>
          
        </ul>
        <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
          <span class="mdi mdi-menu"></span>
        </button>
      </div>
    </nav>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
     
      
      <!-- partial -->
      <!-- partial:partials/_sidebar.html -->
       <%@ include file="navbar.jsp" %>
      <!-- partial -->
      <div class="main-panel">
      
      
        <div class="content-wrapper">
          
             <div class="row">
            <div class="col-lg-12">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">User Form</h4>
                  <%if(request.getParameter("updateId")==null){ %>
                  <form class="cmxform" id="signupForm" onsubmit="return checkUserPassword(this)" method="post" action="UserServlet" enctype="multipart/form-data">
                    <fieldset>
                      <div class="form-group">
                        <label for="firstname">Username</label>
                        <input id="firstname" required class="form-control" name="username" type="text">
                      </div>
                      <div class="form-group">
                        <label for="password">Password</label>
                        <input id="password" required class="form-control" name="password" type="password">
                      </div>
                      <div class="form-group">
                        <label for="confirm_password">Confirm password</label>
                        <input id="confirm_password"  required class="form-control" name="confirm_password" type="password">
                      </div>
                       <div class="form-group">
                      <label for="exampleSelectGender">User Type</label>
                        <select required class="form-control" id="exampleSelectGender" name="usertype">
                        	<option>Select UserType</option>
                           <% ArrayList<UserTypeBean> userTypeList=new UserTypeDaoImp().getAllUserType(); 
                           		for(UserTypeBean userType:userTypeList){
                           	%>
                           		<option value="<%=userType.getUserTypeId() %>"><%=userType.getName()%></option>
                           <%
                           		}
                           %>		
                           
                        </select>
                      </div>
                      <div class="form-group">
                      <label>File upload</label>
                      <input type="file" name="img"   class="file-upload-default">
                      <div class="input-group">
                        <input type="text" class="form-control file-upload-info" disabled placeholder="Upload Image">
                        <span class="input-group-append">
                          <button class="file-upload-browse btn btn-primary" type="button">Upload</button>
                        </span>
                      </div>
                    </div>
                      <div class="form-group">
                        <label for="name">Name</label>
                        <input id="name" required class="form-control" name="name" type="text">
                      </div>
                      <div class="form-group">
                        <label for="password">Address</label>
                        <input id="password" required class="form-control" name="address" type="text">
                      </div>
                      
                        <div class="form-group">
                      <label for="exampleInputMobile">Mobile</label>
                      
                        <input type="text" class="form-control" required id="exampleInputMobile" placeholder="Mobile number" name="number">
                    </div>
                        <div class="form-group">
                      <label for="question" >Question</label>
                      
                        <select class="form-control" required id="question" name="question">
                        	<option>Select Question</option>
                          <%
                          		ArrayList<QuestionBean> questionList=new QuestionDaoImp().getAllQuestion();
                    		  for(QuestionBean question:questionList){
                          %>
                          	<option value="<%=question.getQuestionId()%>"><%=question.getName() %></option>
   						  <%
   						  }
   						  %>	                       
                        </select>
                      </div>
                      
                      <div class="form-group">
                        <label for="answer">Answer</label>
                        <input id="answer" required class="form-control" name="answer" type="text">
                      </div>
                      <input class="btn btn-primary" type="submit" value="Submit" >
                    </fieldset>
                  </form>
                  <%}
                  else{
                  		UserBean userBean=new UserDaoImp().getUserById(Encryption.decrypt(request.getParameter("updateId")));
                 			System.out.println("Hi there");
                  		%>
                  		       <form class="cmxform" id="signupForm" method="post" action="UserServlet?updateId=<%=Encryption.encrypt(userBean.getUserId())%>" enctype="multipart/form-data">
                    <fieldset>
                      <div class="form-group">
                        <label for="firstname">Username</label>
                        <input id="firstname" required class="form-control" name="username" type="text" value="<%=userBean.getUserName()%>">
                      </div>
                      <div class="form-group">
                        <label for="password">Password</label>"
                        <input id="password" required class="form-control" name="password" type="password" value="<%=userBean.getPassword()%>" >
                      </div>
                      <div class="form-group">
                        <label for="confirm_password">Confirm password</label>
                        <input id="confirm_password" required class="form-control" name="confirm_password" type="password" value="<%=userBean.getPassword()%>">
                      	<p id="error"></p>
                      </div>
                       <div class="form-group">
                      <label for="exampleSelectGender">User Type</label>
                        <select class="form-control"  required id="exampleSelectGender" name="usertype">
                        	<option>Select UserType</option>
                           <% ArrayList<UserTypeBean> userTypeList=new UserTypeDaoImp().getAllUserType(); 
                           		for(UserTypeBean userType:userTypeList){
                           			if(userType.getName().equals(userBean.getUserType().getName())){
                           				%>
                           				<option selected value="<%=userType.getUserTypeId() %>"><%=userType.getName()%></option>
                           			<% 	
                           			}
                           			else{
                           	%>
                           		<option value="<%=userType.getUserTypeId() %>"><%=userType.getName()%></option>
                           <%
                           			}}
                           %>		
                           
                        </select>
                      </div>
                      <div class="form-group">
                      <label>File upload</label>
                      <input type="file" name="img"   class="file-upload-default" value="<%=userBean.getImage()%>">
                      <div class="input-group">
                        <input type="text" class="form-control file-upload-info" disabled placeholder="Upload Image" value="<%=userBean.getImage()%>">
                        <span class="input-group-append">
                          <button class="file-upload-browse btn btn-primary" type="button">Upload</button>
                        </span>
                      </div>
                    </div>
                      <div class="form-group">
                        <label for="name">Name</label>
                        <input id="name" required class="form-control" name="name" type="text" value="<%=userBean.getName()%>">
                      </div>
                      <div class="form-group">
                        <label for="password">Address</label>
                        <input id="answer" required  class="form-control" name="address" type="text" value="<%=userBean.getAddress()%>">
                      </div>
                      
                        <div class="form-group">
                      <label for="exampleInputMobile">Mobile</label>
                      
                        <input type="text" class="form-control" required id="exampleInputMobile" placeholder="Mobile number" name="number" value="<%=userBean.getContact()%>">
                    </div>
                        <div class="form-group">
                      <label for="question">Question</label>
                      
                        <select class="form-control" required id="question" name="question">
                        	<option>Select Question</option>
                          <%
                          		ArrayList<QuestionBean> questionList=new QuestionDaoImp().getAllQuestion();
                    		  for(QuestionBean question:questionList){
                    			  if(question.getName().equals(userBean.getQuestion().getName())){
                          %>
                          		<option selected value="<%=question.getQuestionId()%>"><%=question.getName() %></option>
                          <%}else{ %>
                          	<option value="<%=question.getQuestionId()%>"><%=question.getName() %></option>
   							<%}}%>	                       
                        </select>
                      </div>
                      
                      <div class="form-group">
                        <label for="answer">Answer</label>
                        <input id="answer"  required class="form-control" name="answer" type="text"  value="<%=userBean.getAnswer()%>">
                      </div>
                      <input class="btn btn-primary" type="submit" value="Update">
                    </fieldset>
                  </form>
                  <% }%>
                </div>
              </div>
            </div>
          </div>
          
          <div class="row">
            <div class="col-lg-12">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">User</h4>
                  <div class="table-responsive">
                    <table class="table" id="order-listing">
                      <thead>
                        <tr>
                          <th>
                            #
                          </th>
                          <th>
                            Username
                          </th>
                          <th>
                           Password
                          </th>
                          <th>
                            User Type
                          </th>
                          <th>
                            Image
                          </th>
                          <th>
                            Name
                          </th>
                          <th>
                            Address
                          </th>
                          <th>
                            Contact
                          </th>
                          <th>
                            question
                          </th>
                           <th>
                            Answer
                          </th>
                          <th>
                            Created By
                          </th>
                          <th>
                            Created Date
                          </th>
                          <th>
                            Modified By
                          </th>
                          <th>
                            Modified Date
                          </th>
                          <th>
                            Update
                          </th>
                          <th>
                            Delete
                          </th>
                        </tr>
                      </thead>
                      <tbody>
                      <%
                      	int count=1;
                        ArrayList<UserBean> userList=new UserDaoImp().getAllUser();
                        for(UserBean user:userList){		
                      %>
                        <tr>
                          <td>
                            <%=count++ %>
                          </td>
                          <td>
                            <%=user.getUserName() %>
                          </td>
                          <td>
                            <%=user.getPassword() %>
                          </td>
                          <td>
                            <%=user.getUserType().getName() %>
                          </td>
                          <td>
                            <%=user.getImage() %>
                          </td>
                          <td>
                            <%=user.getName() %>
                          </td>
                          <td>
                            <%=user.getAddress() %>
                          </td>
                          <td>
                            <%=user.getContact() %>
                          </td>
                          <td>
                            <%=user.getQuestion().getName() %>
                          </td>
                          <td>
                            <%=user.getAnswer() %>
                          </td>
                          <td>
                            <%=user.getCreatedBy() %>
                          </td>
                          <td>
                            <%=user.getCreatedDate() %>
                          </td>
                          <td>
                            <%=user.getModifiedBy() %>
                          </td>
                          <td><%= user.getModifiedDate() %></td>
                           <td>
                           <form action="index.jsp" method="get">
                           <input type="hidden" name="updateId" value="<%=Encryption.encrypt(user.getUserId())%>">
                              <button class="btn btn-outline-primary">Update</button>
                           </td>
                           </form>
                           <td>
                           <form action="UserServlet" method="get">
                           <input type="hidden" name="deleteId" value="<%=Encryption.encrypt(user.getUserId())%>">
                              <button class="btn btn-outline-primary">Delete</button>
                           </td>
                           </form>
                        </tr>
                        <%}%>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
            
            
          </div>
         
        </div>
        <!-- content-wrapper ends -->
        <!-- partial:partials/_footer.html -->
 <%@ include file="footer.jsp" %>

