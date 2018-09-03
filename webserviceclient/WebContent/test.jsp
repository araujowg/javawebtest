<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form id="frm" name="frm" action="AddLogin" method="post">
	<input type="text" name="addLogin" id="addLogin" />
	<input type="text" name="addEmail" id="addEmail" />
	<input type="password" name="addPassword" id="addPassword" />
	<input type="submit" name="AddUser" id="AddUser" />
</form>


<form id="frm" name="frm" action="DoLogin" method="post">
	<input type="text" name="login" id="login" />
	<input type="password" name="password" id="password" />
	<input type="submit" name="DoLogin" id="DoLogin" />
</form>

</body>
</html>