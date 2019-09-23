<%@page import="com.geeks.servlet.LoginServlet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ include file="header.jsp" %>
 <%! String filePath; %>
 <%
 	System.out.print("session"+request.getSession().getAttribute("username"));
 	if(request.getSession().getAttribute("username")==null){
 		System.out.print(request.getSession().getAttribute("username"));
 		RequestDispatcher dis=request.getRequestDispatcher("login.jsp");
 		dis.forward(request, response);
 	}
 	
 	filePath = getServletContext().getInitParameter("file-upload");
 %>
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
              <img src="<%=filePath+=LoginServlet.userBean.getImage()%>" alt="profile"/>
              <%
              	System.out.println("path="+filePath);
              	System.out.println(LoginServlet.userBean.getName());
              %>
              <span class="nav-profile-name"><%=LoginServlet.userBean.getName().concat("Zakir") %></span>
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
               
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="LogoutServlet">
                <i class="mdi mdi-logout text-primary"></i>
                Logout
              </a>
            </div>
          </li>
          <li class="nav-item nav-settings d-none d-lg-flex">
            <a class="nav-link" href="#">
              <i class="mdi mdi-dots-horizontal"></i>
            </a>
          </li>
        </ul>
        <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
          <span class="mdi mdi-menu"></span>
        </button>
      </div>
    </nav>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:partials/_settings-panel.html -->
      
         
      <!-- partial -->
      <!-- partial:partials/_sidebar.html -->
      <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <ul class="nav">
          <li class="nav-item">
            <a class="nav-link" href="index.html">
              <i class="mdi mdi-view-quilt menu-icon"></i>
              <span class="menu-title">Dashboard</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="widgets/widgets.html">
              <i class="mdi mdi-airplay menu-icon"></i>
              <span class="menu-title"></span>
            </a>
          </li>
           <div class="main-panel">
        <div class="content-wrapper">
          <div class="col-lg-5 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Profits</h4>
                  <div class="table-responsive">
                    <table class="table">
                      <tbody>
                        <tr>
                          <td class="border-0 pt-0">
                            <img src="../../images/dashboard/brand-logo-1.png" alt="icon"/>
                          </td>
                          <td class="border-0 pt-0">
                            <p>Dribbble</p>
                            <p class="text-muted mb-0">North Jermain</p>
                          </td>
                          <td class="text-primary border-0 pt-0">
                            21760
                          </td>
                        </tr>
                        <tr>
                          <td>
                            <img src="../../images/dashboard/brand-logo-2.png" alt="icon"/>
                          </td>
                          <td>
                            <p>Adidas</p>
                            <p class="text-muted mb-0">Bahamas</p>
                          </td>
                          <td class="text-primary">
                            17602
                          </td>
                        </tr>
                        <tr>
                          <td>
                            <img src="../../images/dashboard/brand-logo-3.png" alt="icon"/>
                          </td>
                          <td>
                            <p>New Kattie</p>
                            <p class="text-muted mb-0">Italy</p>
                          </td>
                          <td class="text-primary">
                            72160
                          </td>
                        </tr>
                        <tr>
                          <td>
                            <img src="../../images/dashboard/brand-logo-4.png" alt="icon"/>
                          </td>
                          <td>
                            <p>Anahiborough</p>
                            <p class="text-muted mb-0">Kyrgyz Republic</p>
                          </td>
                          <td class="text-primary">
                            62170
                          </td>
                        </tr>
                        <tr>
                          <td>
                            <img src="../../images/dashboard/brand-logo-5.png" alt="icon"/>
                          </td>
                          <td>
                            <p>Schoenberg</p>
                            <p class="text-muted mb-0">Bulgaria</p>
                          </td>
                          <td class="text-primary">
                            12760
                          </td>
                        </tr>
                        <tr>
                          <td>
                            <img src="../../images/dashboard/brand-logo-6.png" alt="icon"/>
                          </td>
                          <td>
                            <p>South Earnestine</p>
                            <p class="text-muted mb-0">Saint Helena</p>
                          </td>
                          <td class="text-primary">
                            21607
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
           <%@ include file="footer.jsp" %>
     
    