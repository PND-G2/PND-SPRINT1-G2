<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Model.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EJEMPLOS JSP</title>
</head>
<body>

	<h1 style="text-decoration: underline">EJEMPLOS PRÁCTICOS JSP</h1>

	<!---------------------------------------------------------->
					<!-- EJEMPLO DE EXPRESIONES -->
	<!---------------------------------------------------------->
	<div>
		<p>La fecha del sistema es: <%=new java.util.Date()%></p>
		
	</div>

	<hr/><hr/>

	<!---------------------------------------------------------->
					<!-- EJEMPLO DE DECLARACIÓN -->
	<!---------------------------------------------------------->

	<div>
		<p>Ejemplo de suma de 5 + 2:</p>

		<%!// Declaración de variables con <%!
			int num1 = 5;
			int num2 = 2;
		%>

		<p>El resultado de la suma es: <%=num1 + num2%></p>

	</div>

	<hr/><hr/>
	
	<div>

		<!---------------------------------------------------------->
					<!-- EJEMPLO DE SCRIPTLETS -->
		<!---------------------------------------------------------->

		<% // Declaración de un jugador, al que se le setea un nombre. 
		Jugador jugador = new Jugador();
		jugador.setNombre("Antonio");
		%>
		<p>El jugador particular se llama: <% out.println(jugador.getNombre()); %></p>

		<hr/><hr/>

		<!---------------------------------------------------------->
					<!-- EJEMPLO DE SCRIPTLETS -->
		<!---------------------------------------------------------->
		<%
		// Creación de un array de tipo Jugador y llamada al método generador de jugadores. 
		Jugador[] jugadores = new Jugador[5];

		jugadores = Controller.Generador.crearJugadores(5, 10);
		%>

		<!---------------------------------------------------------->
					<!-- EJEMPLO DE SCRIPTLETS -->
		<!---------------------------------------------------------->
		<p>Los jugadores del array son los siguientes:</p>
		<%
		for (int i = 0; i < jugadores.length; i++) {
			out.println(jugadores[i].getNombre() + " " + jugadores[i].getApellidos() + "<br/>");
		}
		%>

	</div>
</body>
</html>
