package controller.pagecontroller;

import java.io.IOException;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Common;
import common.Constants;
import service.HotelFactory;

/**
 * Servlet implementation class ModificarTarifas
 */
@WebServlet("/ModificarTarifas")
public class ModificarTarifas extends HttpServlet implements PageControllerG3 {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarTarifas() {
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
		
		//recogemos las tarifas nuevas y pasamos a Hotelfactory para cambiar
		try {
			double simple = Double.parseDouble(request.getParameter(Constants.SIMPLE));
			double doble =  Double.parseDouble(request.getParameter(Constants.DOBLE));
			double matrimonio =  Double.parseDouble(request.getParameter(Constants.MATRIMONIO));
			
			HotelFactory.newInstance().modificarTarifa(Constants.SIMPLE, simple);
			HotelFactory.newInstance().modificarTarifa(Constants.DOBLE, doble);
			HotelFactory.newInstance().modificarTarifa(Constants.MATRIMONIO, matrimonio);
			
			//intercambiamos las tarifas en Context de aplicación por nuevas
			getServletContext().setAttribute("tarifas", new TreeMap<String, Object>(HotelFactory.newInstance().consultarPrecios()));
		} catch (NumberFormatException nfe) {
			System.out.println(">>>>PageController: ModificarTarifas EXCEPTION: " + nfe.toString());
		}
	
		request.setAttribute("quees", "estarifas");
		request.setAttribute("habs", Common.getHotel().getHabitaciones());
		getServletContext().getRequestDispatcher("/gestionrecepcionista.jsp").forward(request, response);
	}
}
