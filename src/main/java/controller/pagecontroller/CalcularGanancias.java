package controller.pagecontroller;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import service.HotelFactory;
 
/**
 * Servlet implementation class ConsultarGanancias
 */
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"propietario"}))
@WebServlet("/CalcularGanancias")
public class CalcularGanancias extends HttpServlet implements PageControllerG3 {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcularGanancias() {
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

	public void hacerTrabajo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println(">>>>PageController: CalcularGanancias");
		request.setAttribute("quees", "eshabitacion");
		
		//recogemos parameter con el mes dado para calcular ganancias y lo pasamos a HotelFactoryFacadeImpl
		String anoMes = (String) request.getParameter("yearMonth");

		if (anoMes != null) {
			//obtenemos las ganancias
			double ganancias = HotelFactory.newInstance().consultarGanancias(anoMes);
			/*
			 * Ahora en Hotel.java rellenamos algunas reservas que cumplen la condición de fechas y estado "CERRADA" (hecha):
			 * para el meses 2017-01, 2016-12, 2016-11, 2016-10, 2016-09, 2016-08, 2016-07, 2016-06, 2016-05, 2016-04
			 */
			
			
	        try {
	        	//formateamos la fecha del mes pedido a la forma comoda para mostrar
	        	LocalDate date1 = LocalDate.parse(anoMes + "-01");
	        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
	        	String formattedAnoMes = date1.format(formatter);
	        	
	        	//si no hay excepciones tenemos las ganancias y fecha correcta en el request atributos
			    request.setAttribute("ganancias", ganancias);
			    request.setAttribute("formattedAnoMes", formattedAnoMes);
			    request.setAttribute("quees", "esganancias");
	        } catch (DateTimeException ex) {
	        	System.out.println(">>>>PageController: CalcularGanancias EXCEPTION: " + ex.toString());
	        }
		}
		
	    request.setAttribute("habs", Common.getHotel().getHabitaciones());
		getServletContext().getRequestDispatcher("/gestionpropietario.jsp").forward(request, response);
	}

}
