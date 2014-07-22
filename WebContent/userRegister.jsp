<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign Up</title>
</head>
<body>
<big><big>Sign Up</big></big></big></big></big><br>
<br>
<br>
<form method="post" action="SignUpComplete" name="SignUpForm">
 Name : <input name="name"> <br>
  <br>
Department : <input name="dept"> <br>
  <br>
Username : <input name="username">&nbsp;&nbsp; <% if (request.getParameter("true").equals("0")) { %>
      This username is already registered
<%}%>
<br>
  <br>
Password : <input name="password" type="password"><br>
  <br>
  <button value="SignUp" name="SignUp">Sign Up</button>&nbsp;<button value="Reset" name="Reset" type="reset">Reset</button><br>
  <br>
</form>

</body>
</html>