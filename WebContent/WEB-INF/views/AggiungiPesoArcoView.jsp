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
	    <form id="form" method="POST" action="${pageContext.request.contextPath}/AggiungiPesoArco">
	        <table border="0">               
                <tr>
	                <td>Valore</td>
	                <td>
	                <select name="valore"> 
			              <c:forEach var = "n" begin = "1" end = "10"> 
			                  <option><c:out value = "${n}"/></option> 
			              </c:forEach>
		            </select> 
		           </td>
	             </tr>
	             
	             <tr>
	               <td>Peso</td>
		            <td>
		               <select name="descrizione"> 
			                 <c:forEach items="${PesiList}" var="pesi">
			                    <option>${pesi.getDescrizione()}</option>
				             </c:forEach>
			            </select> 
			        </td>
	             </tr>
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