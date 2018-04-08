<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                   
    <h4>Modifica del nodo</h4>
    
    <p class="errore">${errorString}</p>
    
     <form method="POST" action="${pageContext.request.contextPath}/ModificaNodo">
        <input type="hidden" name="Id" value="${nodo.getId()}" />
        <table border="0">
           <tr>
              <td>Beacon Id</td>
              <td><input type="text" name="BeaconId" value="${nodo.getBeaconId()}" /></td>
           </tr>
           <tr>
              <td>Piano</td>
              <td>
                <select id="bv" name="bevanda" size="1">
                    <option selected>${nodo.getmappaId()}</option>
                    <c:forEach var = "i" begin = "1" end = "${piani.size()}">
	                   <option>${ListPiani.get(i)}</option>
	                </c:forEach>
                </select> 
               </td>
           </tr>
           <tr>
              <td>X</td>
              <td><input type="text" name="X" value="${nodo.getX()}" /></td>
           </tr>
           <tr>
              <td>Y</td>
              <td><input type="text" name="Y" value="${nodo.getY()}" /></td>
           </tr>
           <tr>
              <td>Tipo Incendio</td>
              <td><input type="text" name="TipoIncendio" value="${nodo.isTipoIncendio()}" /></td>
           </tr>
           <tr>
              <td>Tipo Uscita</td>
              <td><input type="text" name="TipoUscita" value="${nodo.isTipoUscita()}" /></td>
           </tr>
           <tr>
              <td colspan = "2">
                  <input type="submit" value="Submit" />
                  <a href="${pageContext.request.contextPath}/ListNodi">Indietro</a>
              </td>
           </tr>
        </table>
     </form>
      
    
    <div id="footer">
       <jsp:include page="../layout/_footer.jsp"></jsp:include>
    </div>
</body>
</html>