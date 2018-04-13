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
	    
	    <h4>Lista degli archi contenuti nel DB: </h4>
	    
	    <a href="${pageContext.request.contextPath}/InserimentoAro"><button type="button">Inserisci nuovo arco</button></a>
	    <br>
	    <br>
	    <table border="1" cellpadding="5" cellspacing="1" >
	      <tr>
	          <th>NodoPartenzaId</th>
	          <th>NodoArrivoId</th>
	          <th>Piano</th>
	          <th>Pesi relativi all'arco</th>
	       </tr>
	       <c:forEach items="${ArchiList}" var="arco" >
	          <tr>
	             <td>${arco.getNodoPartenza().getId()}</td>
	             <td>${arco.getNodoArrivo().getId()}</td>
	             <td>${arco.getMappaId()}</td>
	             
	             <td>
	                 <table  border="1" cellpadding="5" cellspacing="1" >
	                 <tr>
	                     <!-- <th></th> -->
		                 <th>Valore</th>
		                 <th>Descrizione</th>
		                 <th>Peso</th>
	                 </tr>
		             <c:forEach items="${arco.getPesi()}" var="P">
		               <tr>
					      <!--   <td>${P.getId()}</td> -->
					       <td>${P.getValore()}</td>
					       <td>${P.getPeso().getDescrizione()}</td> 
					       <td>${P.getPeso().getPeso()}</td> 
					       <td>
	                       <a href="${pageContext.request.contextPath}/">Edit Peso</a>
	                       </td>
				       </tr>
	                 </c:forEach>
	                 </table> 
                 </td>
                 
	             
	             <td>
	                <a href="${pageContext.request.contextPath}/ModificaArco?id=${arco.getId()}">Edit</a>
	             </td>
	             <td>
	                <a id="delete" href="#" onclick="elimina(event,'${pageContext.request.contextPath}/EliminaArco?id=${nodo.getId()}','${nodo.getBeaconId()}')">Delete</a>
	             </td>
	          </tr>
	       </c:forEach>   
	    </table>
	    
	    <div id="footer">
	       <jsp:include page="../layout/_footer.jsp"></jsp:include>
	    </div>
	    
	</body>
</html>