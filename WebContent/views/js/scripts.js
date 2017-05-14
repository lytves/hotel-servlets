function funcionPopup(code, servlet) {
	var element = document.getElementById(code);

	if (code == "popup-filtro") {
		document.getElementById("popup-nif").style.visibility = "hidden";
		document.getElementById("popup-tarifas").style.visibility = "hidden";
	} else if (code == "popup-nif") {
		document.getElementById("popup-filtro").style.visibility = "hidden";
		document.getElementById("popup-tarifas").style.visibility = "hidden";

		var arr = document.forms.nifs.action.split('/');
		var actionServlet = arr[arr.length - 1];

		if (actionServlet != servlet) {
			if (servlet == "cons-checkinout") {
				document.forms.nifs.action = servlet;
				document.getElementById("title-nif").innerHTML = "Consultar para Check-In/Out";
				document.forms.nifs.nifsubmitonclick.setAttribute("onclick", "funcionPopup('popup-nif', 'cons-checkinout');");
			} else if (servlet == "cons-reservadas") {
				document.getElementById("title-nif").innerHTML = "Habitaciones Reservadas";
				document.forms.nifs.action = servlet;
				document.forms.nifs.nifsubmitonclick.setAttribute("onclick", "funcionPopup('popup-nif', 'cons-reservadas');");
			}
			if ((element.style.visibility === 'hidden') || (element.style.visibility === ''))
				element.style.visibility = 'visible';
			return;
		}

	} else if (code == "popup-tarifas") {
		document.getElementById("popup-filtro").style.visibility = "hidden";
		document.getElementById("popup-nif").style.visibility = "hidden";
	} else if (code == "popup-ganancias") {
		var elementOcultar = document.getElementById("popup-ganancias-resultados");
		if (elementOcultar) elementOcultar.style.visibility = "hidden";
	}

	if (element.style.visibility === 'visible') {
		element.style.visibility = 'hidden';
	} else {
		element.style.visibility = 'visible';
	}

}