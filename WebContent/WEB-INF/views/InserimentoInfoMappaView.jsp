<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="entity.Peso" %>
<%@ page import="java.lang.*" %>

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
	                   
	    <h4>Inserimento nuova Mappa</h4>
	    <p> Qui puoi inserire le info relative alla mappa.
	        In seguito verrai reindirizzato alla mappa per l aggiunta delle altre info relative ad archi e nodi
	    <p>
	    <br>
	    
	    <p class="errore">${errorString}</p>
	    
	     <form id="form" method="POST" action="${pageContext.request.contextPath}/InserimentoInfoMappa"  enctype="multipart/form-data">
	        <table border="0">
	           <tr>
	              <td>Piano</td>
	              <td>
		              <select  name="piano" >
			              <option>140</option>
			              <option>145</option>
			              <option>150</option>
			              <option>155</option>
			              <option>160</option>
		              </select>
	              </td>
	           </tr>
	           <tr>
	              <td>Piantina</td>
	              <td>
	              <input type="file" name="piantina" accept=".png">
	              </td>       
	           <tr>
	              <td colspan = "2">
	                  <button onclick="confirmActionForm(event)">Fatto</button>
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