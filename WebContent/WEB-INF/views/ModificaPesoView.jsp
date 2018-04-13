<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="entity.Peso" %>


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
	                   
	    <h4>Modifica del Peso</h4>
	    
	    <p class="errore">${errorString}</p>
	    
	     <form id="form" method="POST" action="${pageContext.request.contextPath}/ModificaPeso">
	        <input type="hidden" name="Id" value="${peso.getId()}" />
	        <table border="0">
	           <tr>
	              <td>Descrizione</td>
	              <td><input type="text" readonly="readonly" name="Descrizione" value="${peso.getDescrizione()}"/></td>
	           </tr>
	           <tr>
	              <td>Peso</td>
	              <td>
	                <select name="Peso"> 
		                <%
			                Peso peso = (Peso) request.getAttribute("peso");
		                    
		                    for(int i=0; i< 10 ; i++){
		                    	if(peso.getPeso() == i)
		                    	  out.print("<option selected>"+i+"</option>");	
		                    	else
		                    	  out.print("<option>"+i+"</option>");		
		                    }
		                %>
		             </select>           
	               </td>	             
	           </tr>
	           <tr>
	              <td colspan = "2">
	                  <button  onclick="confirmActionForm(event)">Fatto</button>
	                  <a id="indietro" href="#" onclick="back(event,'${pageContext.request.contextPath}/ListPesi')">Indietro</a>
	              </td>
	           </tr>
	        </table>
	     </form>     
	    
	    <div id="footer">
	       <jsp:include page="../layout/_footer.jsp"></jsp:include>
	    </div>
	</body>
</html>