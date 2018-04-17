<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="entity.Arco" %>
<%@ page import="entity.Nodo" %>
<%@ page import="java.lang.*" %>

<% 
     Arco arco = null;
     
     if(request.getAttribute("arco") != null)
        arco =(Arco) request.getAttribute("arco");
      
     ArrayList<Nodo> nodi = (ArrayList<Nodo>) request.getAttribute("NodiList");
     
%>

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
	                   
	    <h4>Inserimento nuovo Arco</h4>
	    <p> Qui puoi inserire un nuovo arco. <a target="_blank" href="${pageContext.request.contextPath}/TabellaNodi">Clicca qui </a> per avere la lista dei nodi <p>
	    <br>
	    
	    <p class="errore">${errorString}</p>
	    
	     <form id="form" method="POST" action="${pageContext.request.contextPath}/InserimentoArco">
	        <table border="0">
	           <tr>
	              <td>NodoPartenzaId</td>
	              <td>
		              <select name="NodoPartenzaId">
		                 <% 
			                if(arco != null)
			                    for(Nodo nodo: nodi)
			                    	if(arco.getNodoPartenza().getId() == nodo.getId())
			                    	   out.print("<option selected>"+nodo.getId()+"</option>");  
			                    	else
			                    		out.print("<option>"+nodo.getId()+"</option>");  
			                else
			                	 for(Nodo nodo: nodi)
			                    	 out.print("<option>"+nodo.getId()+"</option>");  
		                %>
		               </select>
	               </td>
	           </tr>
	           <tr>
	              <td>NodoArrivoId</td>
	              <td>
		              <select name="NodoArrivoId">
		                 <% 
			                if(arco != null)
			                    for(Nodo nodo: nodi)
			                    	if(arco.getNodoArrivo().getId() == nodo.getId())
			                    	   out.print("<option selected>"+nodo.getId()+"</option>");  
			                    	else
			                    		out.print("<option>"+nodo.getId()+"</option>");  
			                else
			                	 for(Nodo nodo: nodi)
			                    	 out.print("<option>"+nodo.getId()+"</option>");  
		                %>
		               </select>
	               </td>
	           </tr>
	           <tr>
	              <td>Piano</td>
	              <td><input name="piano" readonly="readonly" type="text" value="${piano}"></td>
	           </tr>
	           <tr>
	              <td><input name="NPesi" readonly="readonly" type="HIDDEN" value="${NPesi}"></td>
	           </tr>
	           <c:forEach var = "i" begin = "0" end = "${NPesi-1}">
	              <td>
		          </td>
	              <tr>
	                <td>Valore</td>
	                <td>
	                <select name="valore${i}"> 
			              <c:forEach var = "n" begin = "1" end = "10"> 
			                  <option><c:out value = "${n}"/></option> 
			              </c:forEach>
		            </select> 
		           </td>
	             </tr>
	             
	             <tr>
	               <td>Peso</td>
		            <td>
		               <select name="descrizione${i}"> 
			                 <c:forEach items="${PesiList}" var="pesi">
			                    <option>${pesi.getDescrizione()}</option>
				             </c:forEach>
			            </select> 
			        </td>
	             </tr>
	           </c:forEach>
	           <tr>
	              <td colspan = "2">
	                  <button onclick="confirmActionForm(event)">Fatto</button>
	                  <a id="indietro" href="#" onclick="back(event,'${pageContext.request.contextPath}/CaricaMappa?piano=${piano}')">Indietro</a>
	              </td>
	           </tr>
	        </table>
	     </form>
	   
	    <div id="footer">
	       <jsp:include page="../layout/_footer.jsp"></jsp:include>
	    </div>
	</body>
</html>