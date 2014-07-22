<%@page import="com.sit.marketplace.broker.utils.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User home</title>
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
if(userName == null) response.sendRedirect("index.jsp");
%>
<h2>Hello <%=userName %></h2>
<p align="right"><a href="logout">Log Out</a></p><br>
</body>
</html>