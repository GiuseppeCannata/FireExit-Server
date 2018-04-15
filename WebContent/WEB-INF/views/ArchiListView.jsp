<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<%@ page import="entity.Mappa" %>

<% Mappa mappa =  (Mappa) request.getSession().getAttribute("mappa");
  pageContext.setAttribute("ArchiList", mappa.getArchi()); %>
	    
<p class="textRiq">Lista degli archi </p>

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
					       <td><a href="${pageContext.request.contextPath}/">Edit Peso</a></td>
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
	    
	   
	    
