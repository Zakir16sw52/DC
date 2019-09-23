<%@page import="com.geeks.util.Encryption"%>
<%@page import="com.geeks.daoimp.QuestionDaoImp"%>
<%@page import="com.geeks.bean.QuestionBean"%>
<%@page import="com.geeks.bean.*"%>
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
                  <form class="cmxform" id="signupForm" method="post" action="UserTypeServlet" >
                    <fieldset>
                      <div class="form-group">
                        <label for="firstname">Name</label>
                        <input id="firstname" class="form-control" name="name" type="text">
                      </div>
                      <input class="btn btn-primary" type="submit" value="Submit">
                    </fieldset>
                  </form>
                  <%}
                  else{
                  		UserTypeBean userBean=new UserTypeDaoImp().getUserTypeById(Encryption.decrypt(request.getParameter("updateId")));
                  %>
                  		       <form class="cmxform" id="signupForm" method="post" action="UserTypeServlet?updateId=<%= Encryption.encrypt(userBean.getUserTypeId())%>" >
                    <fieldset>
                      <div class="form-group">
                        <label for="firstname">Name</label>
                        <input id="firstname" class="form-control" name="name" type="text" value="<%=userBean.getName()%>">
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
                  <h4 class="card-title">Department</h4>
                  <div class="table-responsive">
                    <table id="order-listing" class="table">
                      <thead>
                        <tr>
                          <th>
                            #
                          </th>
                          <th>
                            Name
                          </th>
                         <th>
                           CreatedBy
                          </th>
                          <th>
                            CreatedDate
                          </th>
                          <th>
                            Modifid By
                          </th>
                          <th>
                            ModifiedDate
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
                        ArrayList<UserTypeBean> userTypeList=new UserTypeDaoImp().getAllUserType();
                        for(UserTypeBean userType:userTypeList){		
                      %>
                        <tr>
                          <td>
                            <%=count++ %>
                          </td>
                          <td>
                            <%=userType.getName() %>
                          </td>
                           
                          <td>
                            <%=userType.getCreatedBy() %>
                          </td>
                          <td>
                            <%=userType.getCreatedDate() %>
                          </td>
                          <td>
                            <%=userType.getModifiedBy() %>
                          </td>
                          <td><%= userType.getModifiedDate() %></td>
                           <td>
                           <form action="usertype.jsp" method="get">
                           <input type="hidden" name="updateId" value="<%=Encryption.encrypt(userType.getUserTypeId())%>">
                              <button class="btn btn-outline-primary">Update</button>
                           </td>
                           </form>
                           <td>
                           <form action="UserTypeServlet" method="get">
                           <input type="hidden" name="deleteId" value="<%=Encryption.encrypt(userType.getUserTypeId())%>">
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

