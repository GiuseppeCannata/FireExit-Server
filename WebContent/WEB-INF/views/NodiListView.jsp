<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ page import="entity.Mappa" %>

<% 
  Mappa mappa =  (Mappa) request.getSession().getAttribute("mappa");
  if(mappa != null){
	  pageContext.setAttribute("NodiList", mappa.getNodi());
	  pageContext.setAttribute("piano", mappa.getPiano());
  }
%>
  
<div>

  <p class="textRiq">Lista dei nodi </p>
	    
   <a href="${pageContext.request.contextPath}/InserimentoNodo?piano=${piano}"><button type="button">Inserisci nuovo nodo</button></a>
   <br>
   <br>
   <table border="1" cellpadding="5" cellspacing="1" >
     <tr>
         <th>ID</th>
         <th>BeaconId</th>
         <th>Piano</th>
         <th>X</th>
         <th>Y</th>
         <th>TipoIncendio</th>
         <th>TipoUscita</th>
      </tr>
      <c:forEach items="${NodiList}" var="nodo" >
         <tr>
            <td>${nodo.getId()}</td>
            <td>${nodo.getBeaconId()}</td>
            <td>${nodo.getmappaId()}</td>
            <td>${nodo.getX()}</td>
            <td>${nodo.getY()}</td>
            <td>${nodo.isTipoIncendio()}</td>
            <td>${nodo.isTipoUscita()}</td>  
		    <td>
	          <a href="${pageContext.request.contextPath}/ModificaNodo?id=${nodo.getId()}">Edit</a>
	        </td>
	        <td>
	          <a id="delete${nodo.getBeaconId()}" href="#" onclick="elimina(event,'${pageContext.request.contextPath}/EliminaNodo?id=${nodo.getId()}','${nodo.getBeaconId()}')">Delete</a>
	        </td>
            
         </tr>
      </c:forEach>   
   </table>
</div>