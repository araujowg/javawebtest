<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="br.com.fws.user.entity.UserInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h3> Users Info... </h3>
<% List<UserInfo> userList = (ArrayList<UserInfo>)request.getAttribute("userList");
 
    for(UserInfo info : userList)
    {
        out.print("Id: " + info.getUserId());
        out.print("<br/>");
        out.print("Name: " + info.getName());
        out.print("<br/>");
        out.print("birthDate: " + info.getBirthDate());
        out.print("<br/>");
        out.print("email: " + info.getEmail());
        out.print("<br/>");
        out.print("<br/>");
    }
 
%>

<h3> Update... </h3>
<form name="Update" id="Update" action="Users" method="post">
	<label>UserId</label>
	<input type="text" id="UserId" name="UserId" />
	<br>
	<label>Name</label>
	<input type="text" id="Name" name="Name" />
	<br>
	<label>birthDate</label>
	<input type="text" id="birthDate" name="birthDate" />
	<br>
	<input type="submit" id="Update" name="Update" value="Save" />
	<br>
</form>


</body>
</html>