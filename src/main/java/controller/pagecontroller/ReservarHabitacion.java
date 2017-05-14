package controller.pagecontroller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import model.Reserva;
import service.ReservaFactory;
 
/**
 * Servlet implementation class ConsultarTarifas
 */
@WebServlet("/ReservarHabitacion")
public class ReservarHabitacion extends HttpServlet implements PageControllerG3 {
	private static final long serialVersionUID = 1L;
	private List<Reserva> habitacioneslist;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservarHabitacion() {
        super();
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
		
		System.out.println(">>>>PageController: ReservarHabitación");
		
		String nif = (String) request.getParameter("nif");
		String nom = (String) request.getParameter("nombre");
		String app = (String) request.getParameter("apellidos");
		
		// se rellena el cliente con los datos del formulario
		Cliente c = new Cliente(null, nif, nom, app);

		int any = Integer.parseInt(request.getParameter("anyoentrada")); 
		int mes = Integer.parseInt(request.getParameter("mesentrada"));
		int dia = Integer.parseInt(request.getParameter("diaentrada"));
		int numdiasestancia = Integer.parseInt(request.getParameter("numdiasestancia"));
		
		// se rellena la reserva con los datos del formulario
		Reserva r = new Reserva (null, LocalDate.of(any, mes, dia), null, numdiasestancia, null, null, c );

		int numhabitacion = Integer.parseInt(request.getParameter("numhabitacion"));

		// Llama a la factoria y invoca el facade que ofrece el servicio de reserva
		habitacioneslist = ReservaFactory.newInstance().reservar(c, r, numhabitacion);
		
		// Inserta la nueva lista de habitaciones en el request y carga la JSP
		request.setAttribute("quees", "esreserva");
		request.setAttribute("habs", habitacioneslist);
		getServletContext().getRequestDispatcher("/gestionrecepcionista.jsp").forward(request, response);
	}

}
