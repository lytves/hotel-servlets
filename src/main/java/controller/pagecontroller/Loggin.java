package controller.pagecontroller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.TreeMap;

import javax.annotation.security.DeclareRoles;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Common;
import service.HotelFactory;

/**
 * Servlet implementation class loguearse
 */
@WebServlet(name="/Loggin",  loadOnStartup = 1, urlPatterns = "/Loggin")
@DeclareRoles({ "recepcionista", "supervisor", "propietario" })
public class Loggin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession misession;

	/**
	 * @return
	 * @see HttpServlet#HttpServlet()
	 */
	public Loggin() {
		super();
	}

	/**
	 * service - es el unico metodo que reune doPost y doGet
	 * 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		misession = request.getSession(true);
		String urlRedirect;
		
		if (misession.getAttribute("Grupo") == null) {

			String n = request.getParameter("username");
			String p = request.getParameter("userpass");

			try {
				request.login(n, p);
				System.out.println(">>>>Process de Login ha pasado bien!");
			} catch (Exception e) {
				System.out.println("Excepcion el login 1: " + e.getMessage());
			}
		}
		
		if (request.isUserInRole("recepcionista")) {
			System.out.println(">>>>Login: recepcionista");
			misession.setAttribute("Grupo", "recepcionista");
			//añadimos las tarifas a Context de aplicación, para que se puede mostrarlas siempre 
			getServletContext().setAttribute("tarifas", new TreeMap<String, Object>(HotelFactory.newInstance().consultarPrecios()));
			urlRedirect = "/gestionrecepcionista.jsp";
			
		} else if (request.isUserInRole("supervisor")) {
			System.out.println(">>>>Login: supervisor");
			misession.setAttribute("Grupo", "supervisor");
			//añadimos las tarifas a Context de aplicación, para que se puede mostrarlas siempre 
			getServletContext().setAttribute("tarifas", new TreeMap<String, Object>(HotelFactory.newInstance().consultarPrecios()));
			urlRedirect = "/gestionrecepcionista.jsp";
			
		} else if (request.isUserInRole("propietario")) {
			System.out.println(">>>>Login: propietario");
			misession.setAttribute("Grupo", "propietario");
			urlRedirect = "/gestionpropietario.jsp";
			
		} else {
			System.out.println("No esta en el grupo");
			urlRedirect = "/loggin.jsp";
		}
		
		request.setAttribute("quees", "eshabitacion");
		request.setAttribute("habs", Common.getHotel().getHabitaciones());
		getServletContext().getRequestDispatcher(urlRedirect).forward(request, response);
	}
}