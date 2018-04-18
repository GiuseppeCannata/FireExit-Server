<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>

	<p class="textRiq">Lista dei nodi</p>

	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>ID</th>
			<th>BeaconId</th>
			<th>Piano</th>
			<th>X</th>
			<th>Y</th>
			<th>TipoIncendio</th>
			<th>TipoUscita</th>
		</tr>
		<c:forEach items="${NodiList}" var="nodo">
			<tr>
				<td>${nodo.getId()}</td>
				<td>${nodo.getBeaconId()}</td>
				<td>${nodo.getmappaId()}</td>
				<td>${nodo.getX()}</td>
				<td>${nodo.getY()}</td>
				<td>${nodo.isTipoIncendio()}</td>
				<td>${nodo.isTipoUscita()}</td>
			</tr>
		</c:forEach>
	</table>
</div>