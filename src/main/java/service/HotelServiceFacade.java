package service;

import java.util.HashMap;

public interface HotelServiceFacade {
	public void modificarTarifa(String tipo, double tarifa);
	public double consultarGanancias(String anoMes);
	public HashMap<String, Double> consultarPrecios();

}
