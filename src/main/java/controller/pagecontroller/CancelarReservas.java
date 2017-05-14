package controller.pagecontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Reserva;
import service.ReservaFactory;


/**
 * Servlet implementation class CancelarReservas
 */
@WebServlet("/CancelarReservas")
public class CancelarReservas extends HttpServlet implements PageControllerG3 {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelarReservas() {
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
		List<Reserva> reservalist = new ArrayList<Reserva>();

		// Se recoge el id de la reserva a cancelar
		int numRef = Integer.parseInt(request.getParameter("numreserva"));
		
		System.out.println(">>>>PageController: CancelarReserva: " + numRef + "ID");
		
		// Llama a la factoria y invoca el servicio de cancelación
		reservalist = ReservaFactory.newInstance().cancelarReserva(numRef);
		
		request.setAttribute("quees", "esreserva");
		request.setAttribute("habs", reservalist);
		getServletContext().getRequestDispatcher("/gestionrecepcionista.jsp").forward(request, response);
//		
	}

}
