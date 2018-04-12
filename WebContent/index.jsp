<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	   <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	   <title>FireExit Server</title>
	   <link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	
	<body>
	    <div id="header">
	       <jsp:include page="WEB-INF/layout/_header.jsp"></jsp:include>
	    </div>
	    
	    <div id="topnav">
	        <jsp:include page="WEB-INF/layout/_topnav.jsp"></jsp:include>
	    </div>
	    
	    <div id="menu">
	      <h4>Come posso aiutarti:</h4>
	        <ul>
	          <li>
	            <a href="${pageContext.request.contextPath}/ListNodi">Lista Nodi</a>
	          </li>
	          <li>
	            <a href="${pageContext.request.contextPath}/ListMappe">Lista Mappe</a>
	          </li>
	        </ul>
	    </div>
	    
	    <div id="footer">
	       <jsp:include page="WEB-INF/layout/_footer.jsp"></jsp:include>
	    </div>
	</body>
</html>