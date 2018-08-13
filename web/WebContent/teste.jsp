<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form name="frmTeste" id="frmTeste" action="SendJsp" method="post"> 
		<input type="text" name="txtTeste" id="txtTeste" />
		<input type="submit" name="btnTeste" id="btnTeste" value="click"/>
	</form>
	<form name="frmTesteI" id="frmTesteI" action="AnotherTeste.jsp" method="post"> 
		<input type="text" name="txtTeste" id="txtTeste" />
		<input type="submit" name="btnTeste" id="btnTeste" value="clickII"/>
	</form>
</body>
</html>