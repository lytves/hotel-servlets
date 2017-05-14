package controller.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Session Bean implementation class AuthFilter - es un filtro controlador si empleados tengan acceso a los casos de uso pedidos
 */
@Stateless
@LocalBean
public class AuthFilter implements Filter {
	private HashMap<String, ArrayList<String>> hashmap;

	/**
	 * Default constructor.
	 */
	public AuthFilter() {
		// TODO Auto-generated constructor stub
		hashmap = new HashMap<>();
		
		// aquí ponemos los nombres de los casos de uso (WebServlets) para dar acceso a estos servletes a los grupos de empleados
		hashmap.put("/cons-disponibles", new ArrayList<String>(Arrays.asList("recepcionista", "supervisor")));
		hashmap.put("/cons-reservadas", new ArrayList<String>(Arrays.asList("recepcionista", "supervisor")));
		hashmap.put("/reservar", new ArrayList<String>(Arrays.asList("recepcionista", "supervisor")));
		hashmap.put("/cons-tarifas", new ArrayList<String>(Arrays.asList("recepcionista", "supervisor")));
		hashmap.put("/cancelar-reserva", new ArrayList<String>(Arrays.asList("recepcionista", "supervisor")));
		hashmap.put("/cons-checkinout", new ArrayList<String>(Arrays.asList("recepcionista", "supervisor")));
		hashmap.put("/checkin", new ArrayList<String>(Arrays.asList("recepcionista", "supervisor")));
		hashmap.put("/checkout", new ArrayList<String>(Arrays.asList("recepcionista", "supervisor")));
		hashmap.put("/modificar-tarifas", new ArrayList<String>(Arrays.asList("supervisor")));
		hashmap.put("/calc-ganancias", new ArrayList<String>(Arrays.asList("propietario")));
	}

	@Override
	public boolean execute(HttpServletRequest request) {
		boolean permitido = false;

		HttpSession misession = request.getSession(true);
		String roleSession = (String) misession.getAttribute("Grupo");
		String webOrigen = request.getServletPath();
		System.out.println("AuthFilter in action! request.getServletPath(): " + request.getServletPath());
		
		if (("/loggin").equals(webOrigen))
			permitido = true;
		else if (roleSession != null) {
			if (hashmap.containsKey(webOrigen)) {
				ArrayList<String> rolesPermitidos = hashmap.get(webOrigen);
				permitido = rolesPermitidos.contains(roleSession);
			}
		}
		return permitido;
	}
}