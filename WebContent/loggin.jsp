<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="views/css/styles.css" type="text/css">
    <script type="text/javascript" src="views/js/scripts.js"></script>
	<title>Loggin de usuario</title>
</head>
	<body>
			<% String grupo = (String) session.getAttribute("Grupo"); %>
			<% if (grupo != null) {response.sendRedirect("loggin");} %>		
		<section class="login">
			<div class="logo"><img alt="Logo del hotel" src="views/images/logo.png"/></div>
			<div class="formulario">
				<h3>Introduzca tu login y contraseña:</h3>
				<form action="loggin" method="post">
					<ul>
						<li>
							<label for="username">Login:</label>  
                            <input type="text" id="username" name="username" required />	
						</li>
						<li>
							<label for="userpass">Contraseña:</label>  
                            <input type="password" id="userpass" name="userpass" required />
						</li>
						<li>
						    <input type="submit" value="Submit" name="submit" id="loggin-submit">
						    <input type="reset" value="Reset" name="reset" id="loggin-reset">
						</li>
					</ul>
				</form>
			</div>
		</section>
	</body>
</html>