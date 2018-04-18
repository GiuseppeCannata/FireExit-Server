<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="entity.Mappa"%>

<% Mappa mappa =  (Mappa) request.getSession().getAttribute("mappa");
  pageContext.setAttribute("ArchiList", mappa.getArchi());
  pageContext.setAttribute("piano", mappa.getPiano());
%>

<p class="textRiq">Lista degli archi</p>

<a id="inserisciArco" href="#"
	onclick="richiestaNumPesi(event,'${pageContext.request.contextPath}/InserimentoArco?piano=${piano}')"><button
		type="button">Inserisci nuovo arco</button></a>
<br>
<br>
<table border="1" cellpadding="5" cellspacing="1">
	<tr>
		<th>NodoPartenzaId</th>
		<th>NodoArrivoId</th>
		<th>Piano</th>
		<th>Pesi relativi all'arco</th>
	</tr>
	<c:forEach items="${ArchiList}" var="arco">
		<tr>
			<td>${arco.getNodoPartenza().getId()}</td>
			<td>${arco.getNodoArrivo().getId()}</td>
			<td>${arco.getMappaId()}</td>
			<td>
				<table border="1" cellpadding="5" cellspacing="1">
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
							<td><a id="delete${P.getId()}" href="#"
								onclick="elimina(event,'${pageContext.request.contextPath}/EliminaPesoArco?id=${P.getId()}&piano=${piano}',${P.getId()})">Elimina
									Peso</a></td>
						</tr>
					</c:forEach>
					<tr>
						<td><a
							href="${pageContext.request.contextPath}/AggiungiPesoArco?id=${arco.getId()}&piano=${piano}"><button
									type="button">Aggiungi</button></a></td>
					</tr>
				</table>
			</td>
			<td><a
				id="delete${arco.getNodoPartenza().getId()}-${arco.getNodoArrivo().getId()}"
				href="#"
				onclick="elimina(event,'${pageContext.request.contextPath}/EliminaArco?id=${arco.getId()}&piano=${piano}&NodoPartenza=${arco.getNodoPartenza().getId()}&NodoArrivo=${arco.getNodoArrivo().getId()}','${arco.getNodoPartenza().getId()}-${arco.getNodoArrivo().getId()}')">Delete</a>
			</td>
		</tr>
	</c:forEach>
</table>



