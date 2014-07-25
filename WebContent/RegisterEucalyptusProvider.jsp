<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Baadal Baazar - Register a Eucalyptus based provider</title>
</head>
<body>
<br><br><br><br>
Enter the new parameters :
<form method="post" action="registerEucalyptusProvider" name="RegisterEucalyptusProvider">
Provider name : <input name="name">  <br>
IP address of CLC  : <input name="clcHost">  <br>
Port of CLC : <input name="clcPort">  <br>
AWS Access Key Id : <input name="awsAccessKeyId">  <br>
Secret Access Key : <input name="secretAccessKey">  <br>
<input name="cloudType" value="EUCALYPTUS" readonly>  <br>
<button value="registerEucalyptusProvider" name="registerEucalyptusProvider">Register this provider</button>&nbsp;<button value="Reset" name="Reset" type="reset">Reset</button><br>
</form>

</body>
</html>