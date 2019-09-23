<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ include file="header.jsp" %>
 <%
	 response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	response.setHeader("pargma", "no-cache");
	response.setHeader("Expires", "0");
 	if(session.getAttribute("username")!=null){
 		System.out.println("redirected");
 		response.sendRedirect("index.jsp");
 	}
 	
 %>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth">
        <div class="row w-100">
          <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left p-5">
              <div class="brand-logo">
                <img src="http://www.urbanui.com/boardsterui/template/images/logo.svg" alt="logo">
              </div>
              <h4>Hello! let's get started</h4>
              <h6 class="font-weight-light">Sign in to continue.</h6>
              <form class="pt-3" action="LoginServlet" method="post">
              
              
              	 
                       
                      <script>
                     				 $(document).ready(function(){
   										setTimeout(function(){
   											showSwal('title-and-text');
  										 }); // 5000 to load it after 5 seconds from page load
										});
</script>
                
                <div class="form-group">
                  <input type="text" name="username" class="form-control form-control-lg" id="exampleInputEmail1" placeholder="Username">
                </div>
                <div class="form-group">
                  <input type="password" name="password" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="Password">
                </div>
                <div class="mt-3">
                  <input type="submit" value="Sign In"class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn"  >SIGN IN</a>
                </div>
                <div class="my-2 d-flex justify-content-between align-items-center">
                  <div class="form-check">
                    <label class="form-check-label text-muted">
                      <input type="checkbox" class="form-check-input">
                      Keep me signed in
                    </label>
                  </div>
                  <a href="#" class="auth-link text-black">Forgot password?</a>
                </div>   
              </form>
            </div>
          </div>
        </div>
      </div>
      <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
 <%@ include file="footer.jsp" %>
    