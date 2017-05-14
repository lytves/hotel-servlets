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
import service.ReservaFactory;

/**
 * Servlet implementation class CheckIn
 */
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"recepcionista", "supervisor"}))
@WebServlet("/ConsultarCheckInOut")
public class ConsultarCheckInOut extends HttpServlet implements PageControllerG3 {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarCheckInOut() {
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

		List<Reserva> reservasList = new ArrayList<Reserva>();
		// Se recoge el NIF del cliente para mostrar sus reservas
		String nif = (String) request.getParameter("nif");	
		
		if (!(nif == "") && !(nif == null)) {
			reservasList = ReservaFactory.newInstance().consultarReservas(nif);
		}
		if ( reservasList.size() <= 0 ) 
			request.setAttribute("listavacia", "El NIF no es correcto o no existe ninguna reserva con este NIF!");
		
		request.setAttribute("quees", "esconscheckinout");
		request.setAttribute("habs", reservasList);
		request.setAttribute("nif", nif);
		getServletContext().getRequestDispatcher("/gestionrecepcionista.jsp").forward(request, response);
	}

}
