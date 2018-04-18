<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FireExit Server</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>

	<div id="header">
		<jsp:include page="../layout/_header.jsp"></jsp:include>
	</div>

	<div id="topnav">
		<jsp:include page="../layout/_topnav.jsp"></jsp:include>
	</div>

	<h1 class="messaggio">${Messaggio}</h1>

	<div id="footer">
		<jsp:include page="../layout/_footer.jsp"></jsp:include>
	</div>

</body>

</html>