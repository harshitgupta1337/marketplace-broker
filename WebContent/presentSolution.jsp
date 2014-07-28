<%@page import="com.sit.marketplace.broker.utils.InstanceReservationsGsonAdapter"%>
<%@page import="com.sit.marketplace.broker.vmallocation.FindVmAllocationSolution"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.sit.marketplace.broker.utils.Utils"%>
<%@page import="com.sit.marketplace.test.types.Solution"%>
<%@page import="com.sit.marketplace.broker.types.InstanceReservation"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.sit.marketplace.broker.utils.ParamsGsonAdapter"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Baadal Baazar - What we can offer</title>
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

FindVmAllocationSolution findVmAllocationSolution = new FindVmAllocationSolution();
List<InstanceReservation> instanceReservations = findVmAllocationSolution.findOptimalSolution(request);
response.getWriter().println("Here's what we can offer : ");
int numOfVms = 0;
double totalMinAvailability = 0.0, totalTrust = 0.0, totalCost = 0.0;
StringBuilder stringBuilder = new StringBuilder();
for(InstanceReservation instanceReservation : instanceReservations){
	totalCost += instanceReservation.getNumOfVms()*instanceReservation.getCostPerVmPerHour();
	totalMinAvailability += instanceReservation.getNumOfVms()*instanceReservation.getMinAvailability();
	totalTrust += instanceReservation.getNumOfVms()*instanceReservation.getTrust();
	numOfVms += instanceReservation.getNumOfVms();
	stringBuilder.append("\nProviderId : "+instanceReservation.getProviderId()+" , VMs : "+instanceReservation.getNumOfVms());
}
stringBuilder.append("\n1. Cost : "+totalCost/numOfVms);
stringBuilder.append("\n2. Minimum Vailability : "+totalMinAvailability/numOfVms);
stringBuilder.append("\n3. Trust : "+totalTrust/numOfVms);

response.getWriter().print(stringBuilder.toString());

final GsonBuilder gsonBuilder = new GsonBuilder();
gsonBuilder.registerTypeAdapter(List.class, new InstanceReservationsGsonAdapter());
final Gson gson = gsonBuilder.create(); 
%>
<form method="post" action="launchInstances" name="LaunchInstances">
<input type="hidden" name="instanceReservations" value=<%=gson.toJson(instanceReservations, List.class) %> />
<button value="launchInstances" name="launchInstances">I accept this deal</button><br>
</form>
</body>
</html>