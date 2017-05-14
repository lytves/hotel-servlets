package controller.pagecontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Reserva;
import service.HabitacionFactory;
import service.ReservaFactory;
 
/**
 * Servlet implementation class ConsultarHabitaciones
 */
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"recepcionista", "supervisor"}))
@WebServlet("/ConsultarHabitacionesReservadas")
public class ConsultarHabitacionesReservadas extends HttpServlet implements PageControllerG3 {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarHabitacionesReservadas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		hacerTrabajo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		hacerTrabajo(request, response);
	}

	@Override
	public void hacerTrabajo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Reserva> habitacioneslist = new ArrayList<Reserva>();
		
		String nif = (String) request.getParameter("nif");
		
		if(nif == "" || nif == null) {
			habitacioneslist = HabitacionFactory.newInstance().consultarHabitacinesReservadas();
		} else {
			habitacioneslist = ReservaFactory.newInstance().consultarReservas(nif);
		}
			
		request.setAttribute("quees", "esreserva");
		request.setAttribute("habs", habitacioneslist);
		getServletContext().getRequestDispatcher("/gestionrecepcionista.jsp").forward(request, response);
	}
}
