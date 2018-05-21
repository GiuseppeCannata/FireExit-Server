<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FireExit Server</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/functions.js"></script>
</head>

<body>
	<div id="header">
		<jsp:include page="../layout/_header.jsp"></jsp:include>
	</div>

	<div id="topnav">
		<jsp:include page="../layout/_topnav.jsp"></jsp:include>
	</div>

	<h2>Mappa</h2>
	<p>Ecco la dashboard della mappa da te scelta.
	   <br>
       Qui potrai inserire nuovi nodi e modificare quelli già presenti.
       <br>
       Inoltre puoi inserire nuovi archi e modificare quelli già esisenti.
    </p>

	<table>
		<tr>
			<td>
				<ul id="infoMappa">
					<li><h4>Quadro generale</h4></li>
					<li><b>Piano:</b></li>
					<li>${mappa.getPiano()}</li>
					<li><b>Nome piantina:</b></li>
					<li>${mappa.getPiantina()}</li>
					<li><b>Stato Emergenza:</b></li>
					<c:choose>
					    <c:when test="${statoEmergenza=='0'}">
					        <img src="../../src/images/StatiMappa/NOemergenza.png" alt="piantina piano" height="40" width="40"> 
					    </c:when>    
					    <c:otherwise>
					       <img src="../../src/images/StatiMappa/SIemergenza.png" alt="piantina piano" height="40" width="40">
					    </c:otherwise>
					</c:choose>
					
				</ul>
			</td>
			<td>
				<div id="viewNodi">
					<jsp:include page="NodiListView.jsp"></jsp:include>
				</div>
			</td>
		</tr>
		<tr>
			<td><img src="../../src\images/${mappa.getPiantina()}.png" alt="piantina piano" height="500" width="400"></td>
			<td>
				<div id="viewArchi">
					<jsp:include page="ArchiListView.jsp"></jsp:include>
				</div>
			</td>
		</tr>
	</table>

	<div id="footer">
		<jsp:include page="../layout/_footer.jsp"></jsp:include>
	</div>
</body>
</html>