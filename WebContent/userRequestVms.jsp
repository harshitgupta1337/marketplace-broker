<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.sit.marketplace.broker.utils.Utils"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Baadal Baazar - Request VMs</title>
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
Presently, the only configuration of VM offered is : <br>
<b>1 Core, 128 MB RAM, 2 GB Disk Space</b><br><br><br><br><br>
<form method="post" action="presentSolution.jsp" name="VMRequestForm">
Enter the number of VMs you wish to request : <input name="noOfVms"> <br>
Max. cost per VM per hour you can offer (in $) : <input name="maxCost"> <br>
Minimum availability required (%) : <input name="minAvailability"> <br>
<button value="FindSolution" name="FindSolution">Find a Solution</button>&nbsp;<button value="Reset" name="Reset" type="reset">Reset</button><br>
</form>
</body>
</html>