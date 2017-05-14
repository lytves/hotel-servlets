<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="views/css/styles.css" type="text/css">
<script type="text/javascript" src="views/js/scripts.js"></script>
<title>Entorno de Propietario</title>
</head>
<body>
	<div class="container">
		<header>
			<div class="top-bar">
				<nav class="user-menu">
					<ul>
						<li style="margin-right: 380px; font-size: 20px">
							<b>	>>>>TODAS LAS HABITACIONES</b>
						</li>
						<% String grupo = (String) session.getAttribute("Grupo"); %>
						<li class="role"><%=grupo %>:</li>
						<li class="user"><a href="#">Nombre Empleado</a></li>
						<li class="logout"><a href="CloseSession">Salir</a></li>
					</ul>
				</nav>
			</div>
			<div class="logo inline">
				<a href="/G3-HotelDeHabitaciones"><img src="views/images/logo.png" alt="Hotel G3" title="Hotel G3" /></a>
			</div>
			<nav class="main-menu inline">
				<ul>
					<li><button class="a" onclick="funcionPopup('popup-ganancias');">Calcular Ganancias</button></li>
				</ul>
			</nav>
		</header>
		
		<section class="content">
			<table>
				<tr>
					<th>Num.</th>
					<th>Tipo</th>
					<th>Número</th>
					<th>Precio</th>
					<th>Estado</th>
				</tr>

				<% int contador =0; String numHab=""; %>

				<c:forEach items="${habs}" var="h">
					<% contador++; %>
					<tr>
						<!-- Todas las habitaciones -->
						<td><c:out value="<%= contador %>" /></td>
						<td><c:out value="${h.getClass().getSimpleName()}" /></td>
						<td><c:out value="${h.numero}" /></td>
						<td><c:out value="${h.precio}" /></td>
						<td><c:out value="${h.estado}" /></td>

					</tr>
				</c:forEach>
			</table>
		</section>

		<div id="popup-ganancias" style="border: 1px solid #999;">
			<div class="title-nif">
				<b>Calcular Ganancias</b>
			</div>
			<%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM"); %>
			<div class="formulari">
				<form action="calc-ganancias">
					<table style="margin-bottom: 20px;">
						<tr>
							<td style="border: 0px;"><label>Elige el mes:</label></td>
							<td style="border: 0px;"><input type="month" required name="yearMonth" max="<%= df.format(new java.util.Date()) %>"></td>
						</tr>
					</table>
					<input type="submit" value="Enviar" onclick="funcionPopup('popup-ganancias');" class="buton-filter">
					<input type="reset" value="Limpiar" class="buton-filter">
					<input type="button" value="Cancelar" onclick="funcionPopup('popup-ganancias');" class="buton-filter">
				</form>
			</div>
		</div>
		
		<% if ((request.getAttribute("quees")).equals("esganancias")) {
			String gananciasResultado = "" + (Double) request.getAttribute("ganancias");
			String formattedAnoMes = "" + (String) request.getAttribute("formattedAnoMes");	
		%>
		<div id="popup-ganancias-resultados" style="border: 1px solid #999; height: 200px;">
			<div class="title-nif">
				<b>Ganancias:</b>
			</div>
			<div class="formulari" style="margin: auto; width: 70%; padding: 10px 0; text-align: center;">
				<table style="margin-bottom: 10px;">
					<tr>
						<th>Fecha:</th>
						<th>Total:</th>
					</tr>
					<tr>
						<td><%= formattedAnoMes %></td>
						<td><%= gananciasResultado %></td>
					</tr>
				</table>
				<input type="button" value="Cancelar" onclick="funcionPopup('popup-ganancias-resultados');" class="buton-filter">
			</div>
		</div>
		<script type="text/javascript">funcionPopup('popup-ganancias-resultados');</script>
		<% } %>

		<section class="comands-page">
			<table class="navegacion">
				<tr>
					<td class="style2" id="nav0" onclick="next()">|&lt;&lt;&nbsp;</td>
					<td class="style2" id="nav1" onclick="next()">&lt;&lt;&nbsp;</td>
					<td class="style2" id="nav2" onclick="next()">&gt;&gt;&nbsp;</td>
					<td class="style2" id="nav3" onclick="next()">&gt;&gt;|&nbsp;</td>
				</tr>
			</table>
		</section>
		<footer>
			<div class="copyright">SoftwareG3 ©2016</div>
		</footer>
	</div>
</body>
</html>