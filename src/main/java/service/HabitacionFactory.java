package service;

 
/**
 * Session Bean implementation class HabitacionFactory
 */
public class HabitacionFactory {

	/**
	 * Default constructor. 
	 */
	public HabitacionFactory() {
	        // TODO Auto-generated constructor stub
	    }

	public static HabitacionServiceFacade newInstance() {
		HabitacionServiceFacade habitacionservice;
		habitacionservice = new HabitacionServiceFacadeImpl();

		// Devuelve un objeto del tipo de la interfaz
		return habitacionservice;

	}

}
