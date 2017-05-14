<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="views/css/styles.css" type="text/css">
<script type="text/javascript" src="views/js/scripts.js"></script>
<title>Entorno de gestión</title>
</head>
<body>
	<div class="container">
		<header>
			<div class="top-bar">
				<nav class="user-menu">
					<ul>
						<li style="margin-right: 380px; font-size: 20px">
						<% if ((request.getAttribute("quees").equals("esreserva")) || (request.getAttribute("quees").equals("esconscheckinout"))) { %>
							<b> >>>>HABITACIONES RESERVADAS</b> 
						<% } else if(request.getAttribute("quees").equals("esdisponible")) { %>
							<b> >>>>HABITACIONES DISPONIBLES</b> 
						<% } else { %> <b>
							>>>>TODAS LAS HABITACIONES</b> <% } %>
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
					<li><button class="a" onclick="funcionPopup('popup-tarifas');">Tarifas</button></li>
					<li><button class="a" onclick="funcionPopup('popup-filtro');">Habitaciones Libres</button></li>
					<li><button class="a" onclick="funcionPopup('popup-nif', 'cons-reservadas');">Habitaciones Reservadas</button></li>
					<li><button class="a" onclick="funcionPopup('popup-nif', 'cons-checkinout');">Check-In/Out</button></li>
				</ul>
			</nav>
		</header>
		<section class="content">
			<table>
				<tr>
					<% 	if((request.getAttribute("quees")).equals("esreserva")) { 
							System.out.println(">>>> HABITACIONES RESERVADAS"); 
							System.out.println(request.getAttribute("quees") + ": " + 
			    							request.getAttribute("quees").equals("esreserva")); %>

					<th>Num.</th>
					<th>Tipo</th>
					<th>Número</th>
					<th>Precio</th>

					<th>Fecha entrada</th>
					<th>Fecha Salida</th>
					<th>Estancia (en dias)</th>
					<th>Estado</th>
					<th style="text-align: center;">Cancelar Reserva</th>

					<% } else if((request.getAttribute("quees")).equals("esdisponible")) { 
			    			System.out.println(">>>>HABITACIONES DISPONIBLEs");
			    			System.out.println(request.getAttribute("quees") + ": " + 
			    							request.getAttribute("quees").equals("esdisponible")); %>

					<th>Num.</th>
					<th>Tipo</th>
					<th>Número</th>
					<th>Precio</th>
					<th style="text-align: center;">Reservar Habitación</th>

					<% } else if((request.getAttribute("quees")).equals("esconscheckinout")) { 
							System.out.println(">>>> HABITACIONES RESERVADAS: CONSULTAR CHECK-IN/OUT"); 
							System.out.println(request.getAttribute("quees") + ": " + 
			    							request.getAttribute("quees").equals("esconscheckinout")); %>
					<th>Num.</th>
					<th>Tipo</th>
					<th>Número</th>
					<th>Precio</th>

					<th>Fecha entrada</th>
					<th>Fecha Salida</th>
					<th>Estancia (en dias)</th>
					<th>Estado</th>
					<th style="text-align: center;">Acción</th>

					<% } else { 
			    			System.out.println(">>>>TODAS LAS HABITACIONES"); 
			    			System.out.println(request.getAttribute("quees") + ": " +
			    							request.getAttribute("quees").equals("esdisponible")); %>

					<th>Num.</th>
					<th>Tipo</th>
					<th>Número</th>
					<th>Precio</th>
					<th>Estado</th>

					<% } %>
				</tr>

				<% int contador =0; String numHab=""; %>

				<c:forEach items="${habs}" var="h">
					<% contador++; %>
					<tr>
						<% 	if((request.getAttribute("quees")).equals("esreserva")) {  %>
						<td><c:out value="<%= contador %>" /></td>

						<td><c:out value="${h.getHabitacion().getClass().getSimpleName()}" /></td>
						<td><c:out value="${h.getHabitacion().numero}" /></td>
						<td><c:out value="${h.getHabitacion().precio}" /></td>

						<td><c:out value="${h.in}" /></td>
						<td><c:out value="${h.out}" /></td>
						<td style="text-align: center;">
							<c:out value="${h.numDias}" />
						</td>
						<td><c:out value="${h.estado}" /></td>
						<td style="text-align: center;">
							<c:if test="${h.estado == 'Activa'}">
									<form action="cancelar-reserva">
										<input type="hidden" name="numreserva" value="${h.id}">
										<input type="submit" class="tr-button" value="Cancelar Reserva">
									</form>
							</c:if>
						</td>

						<% } else if((request.getAttribute("quees")).equals("esdisponible")) { %>
						<td><c:out value="<%= contador %>" /></td>
						<td><c:out value="${h.getClass().getSimpleName()}" /></td>
						<td><c:out value="${h.numero}" /></td>
						<td><c:out value="${h.precio}" /></td>
						<td style="text-align: center;">
							<input type="hidden" name="numhabitacion" value="${h.numero}">
							<button class="tr-button" onclick="funcionPopup('popup-reserva');">Reservar</button>
							<div id="popup-reserva">
								<div class="title">
									<b>- Reservas -</b>
								</div>
								<div class="formulari">
									<div style="float: left; width: 160px;">
										<label>NIF *</label><br> 
										<label>Nombre *</label><br>
										<label>Apellidos *</label><br> 
										<label>Fecha Entrada *</label><br> 
										<label>Estancia (en dias) *</label><br>
										<label>Comentarios</label>
									</div>
									
									<div style="text-align: left;">
										<form action="reservar">
											<input type="hidden" name="numhabitacion" value="${h.numero}">
											<input type="text" name="nif" min="9" max="9" required style="width: 200px;"><br> 
											<input type="text" name="nombre" required style="width: 200px;"><br>
											<input type="text" name="apellidos" required style="width: 200px;"><br> 
											<input type="number" name="diaentrada" value="${diaentrada}"  min="1" max="30" required  style="width: 40px;"> 
											<input type="number" name="mesentrada" value="${mesentrada}"   min="1" max="12" required style="width: 40px;"> 
											<input type="number" name="anyoentrada" value="${anyoentrada}"   min="2017" max="2020" required style="width: 60px;"><br> 
											<input type="number" name="numdiasestancia" value="${numdiasestancia}"  min="1" max="60" required  style="width: 50px;"><br>
											<textarea name="comentario" rows="4" cols="40" style="resize: none;"></textarea>
	
											<br style="line-height: 45px;" /> 
											<input type="submit" value="Enviar" onclick="funcionPopup('popup-reserva');" class="buton"> 
											<input type="reset" value="Limpiar" class="buton"> 
											<input type="button" value="Cancelar" onclick="funcionPopup('popup-reserva');" class="buton">
										</form>
									</div>
								</div>
							</div>
						</td>
							
						<% } else if((request.getAttribute("quees")).equals("esconscheckinout")) {  %>
						<td><c:out value="<%= contador %>" /></td>
						<td><c:out value="${h.getHabitacion().getClass().getSimpleName()}" /></td>
						<td><c:out value="${h.getHabitacion().numero}" /></td>
						<td><c:out value="${h.getHabitacion().precio}" /></td>

						<td><c:out value="${h.in}" /></td>
						<td><c:out value="${h.out}" /></td>
						<td style="text-align: center;"><c:out value="${h.numDias}" /></td>
						<td><c:out value="${h.estado}" /></td>
						<td style="text-align: center;">
						<% String nif = (String) request.getAttribute("nif"); %>
							<c:choose>
								<c:when test="${h.estado == 'Activa'}">
									<form action="checkin">
										<input type="hidden" name="numreserva" value="${h.id}">
										<input type="hidden" name="nif" value="<%=nif%>">
										<input type="submit" class="tr-button" value="Check-In">
									</form>
								</c:when>
								<c:when test="${h.estado == 'En Progreso'}">
									<form action="checkout">
										<input type="hidden" name="numreserva" value="${h.id}">
										<input type="hidden" name="nif" value="<%=nif%>">
										<input type="submit" class="tr-button" value="Check-Out">
									</form>
								</c:when>
							</c:choose>
						</td>
						
						<% } else { %>
						<!-- Todas las habitaciones -->
						<td><c:out value="<%= contador %>" /></td>
						<td><c:out value="${h.getClass().getSimpleName()}" /></td>
						<td><c:out value="${h.numero}" /></td>
						<td><c:out value="${h.precio}" /></td>
						<td><c:out value="${h.estado}" /></td>

						<% } %>

					</tr>
				</c:forEach>
				<c:if test="${fn:length(habs) == 0}">
   					<tr><th colspan="10" style="text-align: center;"><%= request.getAttribute("listavacia")%></th></tr>
				</c:if>
			</table>
		</section>

		<div id="popup-filtro">
			<div class="title-filter">
				<b>- Filtros -</b>
			</div>
			
			<div class="formulari">
				<div style="float: left; width: 160px;">
					<label>Tipo Habitación</label><br> 
					<label>Fecha de entrada</label><br> 
					<label>Estancia (en dias)</label><br>
				</div>
			</div>
			
			<div class="formulari">
				<form action="cons-disponibles" id="filtros">
					<select name="tipohabitacion" form="filtros">
						<option value=""></option>
						<option value="Simple">Simple</option>
						<option value="Doble">Doble</option>
						<option value="Matrimonio">Matrimonio</option>
					</select><br /> 
					<input type="number" name="diaentrada" min="1" max="30" style="width: 40px;"> 
					<input type="number" name="mesentrada" min="1" max="12" style="width: 40px;"> 
					<input type="number" name="anyoentrada" min="2017" max="2020" style="width: 60px;"><br/> 
					<input type="number" name="numdiasestancia" min="1" max="60" style="width: 50px;"><br/>

					<input type="submit" value="Enviar" onclick="funcionPopup('popup-filtro');" class="buton-filter">
					<input type="reset" value="Limpiar" class="buton-filter"> 
					<input type="button" value="Cancelar" onclick="funcionPopup('popup-filtro');" class="buton-filter">

				</form>
			</div>
		</div>

		<div id="popup-nif">
			<div class="title-nif">
				<b><span id="title-nif">Habitaciones Reservadas</span> -
					Busqueda por NIF</b>
			</div>
			<div class="formulari">
				<div style="float: left; width: 150px;">
					<label>NIF del Cliente:</label><br>
				</div>
			</div>
			<div class="formulari">
				<form action="cons-reservadas" name="nifs" id="nifs">
					<input type="text" name="nif" style="width: 100px;"><br /><br /> 
					<input type="submit" value="Enviar" onclick="funcionPopup('popup-nif');" class="buton-filter">
					<input type="reset" value="Limpiar" class="buton-filter"> 
					<input type="button" value="Cancelar" name="nifsubmitonclick" onclick="funcionPopup('popup-nif', 'cons-reservadas');" class="buton-filter">
				</form>
			</div>
		</div>
		
		<div id="popup-tarifas">
			<div class="title-nif">
				<b>Consultar Tarifas</b>
			</div>
			<div class="formulari" style="padding-left: 0; text-align: center;">
				<form action="modificar-tarifas" name="nifs" id="nifs">
				<div style="margin: auto; width: 70%; padding-bottom: 10px;">
					<table>
						<tr>
							<th>Tipo:</th>
							<th>Precio:</th>
						</tr>
						<% String readonly = "readonly "; %>
						<% if (("supervisor").equalsIgnoreCase(grupo)) {readonly = " ";} %>
						<c:forEach items="${tarifas}" var="tarifa">
 						<tr>
 							<td><label style="width: 50%;">${tarifa.key}:</label></td>
 							<td><input type="text" name="${tarifa.key}"<%=readonly %>value="${tarifa.value}" required style="width: 50px; margin-left: 30px;"></td>
 						</tr>
						</c:forEach>
					</table>
				</div>
					<% if (("supervisor").equalsIgnoreCase(grupo)) { %>
					<input type="submit" value="Enviar" class="buton-filter">
					<input type="reset" value="Limpiar" class="buton-filter"> 
					<% } %>
					<input type="button" value="Cancelar" onclick="funcionPopup('popup-tarifas');" class="buton-filter">
				</form>
			</div>
		</div>

		<section class="comands-page">
			<table class="navegacion">
				<tr>
					<td class="style2" id="nav0" onclick="nextPagina()">|&lt;&lt;&nbsp;</td>
					<td class="style2" id="nav1" onclick="nextPagina()">&lt;&lt;&nbsp;</td>
					<td class="style2" id="nav2" onclick="nextPagina()">&gt;&gt;&nbsp;</td>
					<td class="style2" id="nav3" onclick="nextPagina()">&gt;&gt;|&nbsp;</td>
				</tr>
			</table>
		</section>

		<footer>
			<div class="copyright">SoftwareG3 ©2016</div>
		</footer>

	</div>
</body>
</html>