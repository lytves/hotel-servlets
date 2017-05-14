package common;

/**
 * Contenido comun a todo el proyecto
 */
public class Common {
	private static Hotel hotel;

	private Common() {
		hotel = new Hotel();
		hotel.init();
	}

	public static Hotel getHotel() {
		return hotel;
	}

	public static void setHotel(Hotel hotel) {
		Common.hotel = hotel;
	}
	
	
}
