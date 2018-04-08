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
    
    <h4>Lista dei nodi contenuti nel DB: </h4>
    
    <table border="1" cellpadding="5" cellspacing="1" >
      <tr>
          <th>BeaconId</th>
          <th>Piano</th>
          <th>X</th>
          <th>Y</th>
          <th>TipoIncendio</th>
          <th>TipoUscita</th>
       </tr>
       <c:forEach items="${NodiList}" var="nodo" >
          <tr>
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
                <a>Delete</a>
             </td>
          </tr>
       </c:forEach>
       
    </table>
    
    <div id="footer">
       <jsp:include page="../layout/_footer.jsp"></jsp:include>
    </div>
</body>
</html>