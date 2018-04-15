<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

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
	    
	    <h4>Lista dei nodi contenuti nel DB: </h4>
	    
	    <a href="${pageContext.request.contextPath}/InserimentoInfoMappa"><button type="button">Inserisci nuova mappa</button></a>
	    <br>
	    <br>
	    <table border="1" cellpadding="5" cellspacing="1" >
	       <tr>
	          <th>Piano</th>
	          <th>Nome Piantina</th>
	       </tr>
	       <c:forEach items="${ListMappe}" var="mappa" >
	          <tr>
	             <td>${mappa.getPiano()}</td>
	             <td>${mappa.getPiantina()}</td>
	             <td> <a href="${pageContext.request.contextPath}/CaricaMappa?piano=${mappa.getPiano()}">Vai</a></td>
	              <td> <a id="delete${mappa.getPiano()}" href="#"  onclick="elimina(event,'${pageContext.request.contextPath}/EliminaMappa?piano=${mappa.getPiano()}','${mappa.getPiano()}')">Delete</a></td>
	          </tr>
	       </c:forEach>   
	    </table>
	    
	    <div id="footer">
	       <jsp:include page="../layout/_footer.jsp"></jsp:include>
	    </div>
	</body>
</html>