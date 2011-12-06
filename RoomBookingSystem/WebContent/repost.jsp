
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@page import = "java.io.*" import = "Test.*" import ="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Upload Room Information</title>
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />
<!--                       Javascripts                       -->
<!-- jQuery -->
<script type="text/javascript" src="resources/scripts/jquery-1.3.2.min.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript" src="resources/scripts/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="resources/scripts/facebox.js"></script>
<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>
<!-- jQuery Datepicker Plugin -->
<script type="text/javascript" src="resources/scripts/jquery.datePicker.js"></script>
<script type="text/javascript" src="resources/scripts/jquery.date.js"></script>
<style type="text/css">
.h3 {
	color: #C0F;
}
</style>
</head>
<body>
<div id="body-wrapper">
  <!-- Wrapper for the radial gradient background -->
  <div id="sidebar">
    <div id="sidebar-wrapper">
      <!-- Sidebar with logo and menu -->
      <h1 id="sidebar-title"><a href="#">UIC</a></h1>
      <!-- Logo (221px wide) -->
      <a href="#"><img id="logo" src="resources/images/logo.png" alt="Simpla Admin logo" /></a>
      <!-- Sidebar Profile links -->
      <div id="profile-links"> Hello, <a href="#" title="Edit your profile">user</a>|
        <a href="login.html" title="Sign Out">Sign out</a> </div>
      <ul id="main-nav">
        <!-- Accordion Menu -->
<li> <a href="index.html" class="nav-top-item no-submenu">
          <!-- Add the class "no-submenu" to menu items with no sub menu -->
          Portal </a> </li>
<li> <a href="query.html" class="nav-top-item no-submenu">
          <!-- Add the class "no-submenu" to menu items with no sub menu -->
          Query and Apply Room </a> </li>
        <li> <a href="myapplication.html" class="nav-top-item no-submenu">
          <!-- Add the class "current" to current menu item -->
          My Applications </a>
          <ul>
          </ul>
        </li>
        <li> <a href="#" class="nav-top-item current">Room Information Management</a>
          <ul>
            <li><a href="upload.html">Upload Room Information</a></li>
            <li><a href="#">Maintain Room Information </a></li>
          </ul>
        </li>
        <li> <a href="manageusers.html" class="nav-top-item no-submenu">User Management </a>
          
        </li>
        <li> <a href="viewatio.html" class="nav-top-item no-submenu"> View Use Ratio </a>
        </li>
      </ul>
      <!-- End #main-nav -->
      <!-- End #messages -->
    </div>
  </div>
  <!-- End #sidebar -->
  <div id="main-content">
    <!-- Main Content Section with everything -->
    <noscript>
    <!-- Show a notification if the user has disabled javascript -->
    <div class="notification error png_bg">
    </noscript>
    <!-- Page Head -->
    <h2>Welcome to Room Booking System</h2>
   
    <ul class="shortcut-buttons-set">
      
    </ul>
    <!-- End .shortcut-buttons-set -->
    <div class="clear"></div>
    <!-- End .clear -->
    <div class="content-box">
      <!-- Start Content Box -->

      <div class="content-box-header">
        <h3 class="h3"> Upload Room Information</h3>
        <ul class="content-box-tabs">
         
          <!-- href must be unique and match the id of target div -->
        </ul>
        <div class="clear"></div>
</div>
      <!-- End .content-box-header -->
<div class="content-box-content">
        <div class="tab-content default-tab" id="tab1">
          <p>upload successfully&#65281;</p>
  </div>
  <!-- End #tab1 -->
      
  <!-- End #main-content -->
</div>

<%String filename = request.getParameter("Excel");
   out.println(filename);
   try {
		String driver_class = "com.mysql.jdbc.Driver";
		String connection_url = "jdbc:mysql://localhost:3306/rbsdb ";
		String user_name = "root";
		String db_password = "123123";
		Class.forName(driver_class);
		Connection conn = DriverManager.getConnection(connection_url, user_name,
				db_password);
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		String sql0 = "delete from rbsdb ";
		stmt.executeUpdate(sql0);
		stmt.close();
		conn.close();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	 Test test =  new Test();
     test.upload(filename); 
   %>
   
   
</body>
<!-- Download From www.exet.tk-->
</html>
  
   
   