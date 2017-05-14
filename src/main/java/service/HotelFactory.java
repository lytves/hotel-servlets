package service;

 
/**
 * Session Bean implementation class HotelFactory
 */
public class HotelFactory {

	/**
	     * Default constructor. 
	     */
	    public HotelFactory() {
	        // TODO Auto-generated constructor stub
	    }

	public static HotelServiceFacade newInstance() {
		HotelServiceFacade hotelservice;
		hotelservice = new HotelServiceFacadeImpl();

		// Devuelve un objeto del tipo de la interfaz
		return hotelservice;

	}

}