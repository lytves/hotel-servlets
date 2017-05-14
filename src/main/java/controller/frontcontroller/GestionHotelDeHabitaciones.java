package controller.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.filters.*;
import common.Common;
import common.Hotel;

/**
 * Servlet implementation class GestionHotelDeHabitaciones - el principal FrontController
 */
@WebServlet(name="GestionHotelDeHabitaciones",  loadOnStartup = 1, urlPatterns={"/loggin"
																				,"/cons-disponibles"
																				,"/cons-reservadas"
																				,"/reservar"
																				,"/cancelar-reserva"
																				,"/cons-checkinout"
																				,"/checkin"
																				,"/checkout"
																				,"/modificar-tarifas"
																				,"/calc-ganancias"})
public class GestionHotelDeHabitaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FilterManager filterManager;
	boolean filterFail = false;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Hotel h = new Hotel();
		h.init();
		Common.setHotel(h);
	}

	public void destroy() {
	}

	/**
	 * service - es el único metodo que reune doPost y doGet
	 * 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		System.out.println("_____________________FRONT CONTROLLER_________________________________________________________________________");
		RequestDispatcher dispatcher;
		String webOrigen = request.getServletPath();
		System.out.println(">>>>>request.getServletPath(): " +  webOrigen);
		System.out.println(">>>>>request.getPathInfo(): " +  request.getPathInfo());
		System.out.println(">>>>>request.getPathTranslated(): " +  request.getPathTranslated());
		System.out.println(">>>>>request.getQueryString(): " +  request.getQueryString());
		System.out.println(">>>>>request.getRequestURI(): " +  request.getRequestURI());
		
		
		//se empieza pasar por grupo de los filtros
		filterManager = new FilterManager();
		filterManager.setFilter(new AuthFilter());
		
	    ClientFilter client = new ClientFilter();
	    client.setFilterManager(filterManager);
	    filterFail = client.sendRequest(request);
	    // si (filterFail == true) significa que por lo menos uno de los filtres falló, entonces...
	    // en el variable "webOrigen" ponemos  valor "/prohibido" para mostrar la página "Acceso Denegado"
	    if (filterFail) webOrigen = "/prohibido";
	    else System.out.println("El filtro pasa, continuar!");
	    //se acaban los filtros
	    
		try {
			switch (webOrigen) {
			case "/loggin":
				System.out.println(">>>>>FrontController: /loggin");
				dispatcher = getServletContext().getRequestDispatcher("/Loggin");
				dispatcher.forward(request, response);
				break;
			case "/cons-disponibles":
				System.out.println(">>>>>FrontController: /cons-disponibles");
				dispatcher = getServletContext().getRequestDispatcher("/ConsultarHabitacionesDisponibles");
				dispatcher.forward(request, response);
				break;
			case "/cons-reservadas":
				System.out.println(">>>>>FrontController: /cons-reservadas");
				dispatcher = getServletContext().getRequestDispatcher("/ConsultarHabitacionesReservadas");
				dispatcher.forward(request, response);
				break;
			case "/reservar":
				System.out.println(">>>>>FrontController: /reservar");
				dispatcher = getServletContext().getRequestDispatcher("/ReservarHabitacion");
				dispatcher.forward(request, response);
				break;
			case "/cons-tarifas":
				System.out.println(">>>>>FrontController: /cons-tarifas");
				dispatcher = getServletContext().getRequestDispatcher("/ConsultarTarifas");
				dispatcher.forward(request, response);
				break;
			case "/cancelar-reserva":
				System.out.println(">>>>>FrontController: /cancelar-reserva");
				dispatcher = getServletContext().getRequestDispatcher("/CancelarReservas");
				dispatcher.forward(request, response);
				break;
			case "/cons-checkinout":
				System.out.println(">>>>>FrontController: /cons-checkinout");
				dispatcher = getServletContext().getRequestDispatcher("/ConsultarCheckInOut");
				dispatcher.forward(request, response);
				break;
			case "/checkin":
				System.out.println(">>>>>FrontController: /checkin");
				dispatcher = getServletContext().getRequestDispatcher("/CheckIn");
				dispatcher.forward(request, response);
				break;
			case "/checkout":
				System.out.println(">>>>>FrontController: /checkout");
				dispatcher = getServletContext().getRequestDispatcher("/CheckOut");
				dispatcher.forward(request, response);
				break;
			case "/modificar-tarifas":
				System.out.println(">>>>>FrontController: /modificar-tarifas");
				dispatcher = getServletContext().getRequestDispatcher("/ModificarTarifas");
				dispatcher.forward(request, response);
				break;
			case "/calc-ganancias":
				System.out.println(">>>>>FrontController: /calc-ganancias");
				dispatcher = getServletContext().getRequestDispatcher("/CalcularGanancias");
				dispatcher.forward(request, response);
				break;
			case "/prohibido":
				System.out.println(">>>>>FrontController: /prohibido");
				dispatcher = getServletContext().getRequestDispatcher("/prohibido.jsp");
				dispatcher.forward(request, response);
				break;
			default:
				System.out.println(">>>>>FrontController: default");
				dispatcher = getServletContext().getRequestDispatcher("/Loggin");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(">>>>>FrontController: Exception");
			System.out.println(e.toString());
		}
	}
}
