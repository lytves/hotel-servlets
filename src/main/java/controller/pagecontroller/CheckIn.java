package controller.pagecontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ReservaFactory;

/**
 * Servlet implementation class CheckIn
 */
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"recepcionista", "supervisor"}))
@WebServlet("/CheckIn")
public class CheckIn extends HttpServlet implements PageControllerG3 {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIn() {
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

		try {
			// Se recoge el id de la reserva para checkin
			int numRef = Integer.parseInt(request.getParameter("numreserva"));
			
			//se hace el checkin por numero de la reserva
			//el atributo "statuscheckinout" se puede utilizar en FrontEnd para mostrat el resultado de operación "Bueno!"
			if (ReservaFactory.newInstance().checkIn(numRef)) request.setAttribute("statuscheckinout", true);
		} catch(NumberFormatException nfe) {
			System.out.println(">>>>PageController: CheckIn EXCEPTION: " + nfe.toString());
		}
			
		getServletContext().getRequestDispatcher("/cons-checkinout").forward(request, response);
	}

}
