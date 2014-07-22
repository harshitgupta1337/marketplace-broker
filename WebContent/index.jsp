<%@page import="com.sit.marketplace.broker.utils.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cloud Marketplace Broker - Enter here</title>
</head>
<body>
<%
String userName = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals(Utils.COOKIE_NAME)) userName = cookie.getValue();
}
}
if(userName != null) response.sendRedirect("userHome.jsp");
%>
<h1>Welcome to Baadal Bazaar !!!!</h1><br><br>
<big><big><big>Login</big></big></big><br>

  
<form method="post" action="checkLogin" name="login">Username :&nbsp;&nbsp;  <input name="username"><br>
  <br>
Password :&nbsp; &nbsp;  <input name="password" type="password"><br>
  <br>
  <button value="Submit" name="Submit">Submit</button>&nbsp;<button value="Reset" name="Reset" type="reset">Reset</button><a href="userRegister.jsp?true=1">Not registered yet ? Sign up</a><br>
  <br>
</form>

</body>
</html>