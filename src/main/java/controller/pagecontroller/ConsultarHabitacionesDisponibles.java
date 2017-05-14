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

import model.Habitacion;
import service.HabitacionFactory;
 
/**
 * Servlet implementation class ConsultarHabitaciones
 */
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"recepcionista", "supervisor"}))
@WebServlet("/ConsultarHabitacionesDisponibles")
public class ConsultarHabitacionesDisponibles extends HttpServlet implements PageControllerG3 {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarHabitacionesDisponibles() {
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
		List<Habitacion> habitacioneslist = new ArrayList<Habitacion>();
		
		// se recogen los filtros de busqueda establecidos en el JSP
		String tipoHabitacion = (String) request.getParameter("tipohabitacion");
		String fechaEntrada_any = (String) request.getParameter("anyoentrada"); 
		String fechaEntrada_mes = (String) request.getParameter("mesentrada");
		String fechaEntrada_dia = (String) request.getParameter("diaentrada");
		String numdiasestancia = (String) request.getParameter("numdiasestancia");
		
		// se invoca la factoria que instancia la SessionFacade que nos ofrece el servicio "consultarHabitacines"
		habitacioneslist = HabitacionFactory.newInstance().consultarHabitacinesDisponibles(tipoHabitacion, fechaEntrada_dia, fechaEntrada_mes, fechaEntrada_any, numdiasestancia);
		
		request.setAttribute("quees", "esdisponible");
		request.setAttribute("habs", habitacioneslist);
		request.setAttribute("anyoentrada", fechaEntrada_any);
		request.setAttribute("mesentrada", fechaEntrada_mes);
		request.setAttribute("diaentrada", fechaEntrada_dia);
		request.setAttribute("numdiasestancia", numdiasestancia);
		getServletContext().getRequestDispatcher("/gestionrecepcionista.jsp").forward(request, response);
	}
}
