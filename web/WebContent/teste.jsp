<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/teste.js"></script>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form name="frmTeste" id="frmTeste" action="ServletPostJson" method="post"> 
		<div class="container">
			<div class="form ">
				<input type="text" name="txtTeste" id="txtTeste" />
				<input type="submit" class="btn btn-info-bg" name="btnTesteS" id="btnTesteS" value="ServletJson"/>
				<input type="button" class="btn btn-info-bg" name="btnTeste" id="btnTeste" value="Teste"/>
			</div>
		</div>
	</form>
	<form name="frmTesteI" id="frmTesteAnotherTeste" action="AnotherTeste.jsp" method="post"> 
		<input type="text" name="txtAnotherTeste" id="txtAnotherTeste" />
		<input type="button" name="btnAnotherTeste" id="btnAnotherTeste" value="AnotherTeste"/>
	</form>
	<form name="frmTesteII" id="frmTesteGetXml" action="GetXml" method="post"> 
		<input type="text" name="txtTesteXml" id="txtTesteXml" />
		<input type="submit" name="btnTesteXml" id="btnTesteXml" value="ServletXml"/>
	</form>
	
	<form name="frmTesteII" id="frmTesteGetMongo" action="ServletGetMongoData" method="GET"> 
		<input type="text" name="txtTesteGetMongo" id="txtTesteGetMongo" />
		<input type="submit" name="btnTesteGetMongo" id="btnTesteGetMongo" value="ServletGetMongoData"/>
	</form>
	
	<form name="frmTesteII" id="frmTesteDynamoDB" action="ServletGetDynamoDB" method="GET"> 
		<input type="text" name="txtTesteDynamoDB" id="txtTesteGetDynamoDB" />
		<input type="submit" name="btnTesteDynamoDB" id="btnTesteGetDynamoDB" value="ServletGetDynamoDB"/>
	</form>
	
	
	
</body>
</html>