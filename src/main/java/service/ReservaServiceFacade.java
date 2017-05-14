package service;

import java.util.List;
 
import model.Cliente;
import model.Reserva;

public interface ReservaServiceFacade {
	public List<Reserva> consultarReservas(String nif);
	public List<Reserva> reservar(Cliente c, Reserva r, int numhabitacion);
	public List<Reserva> cancelarReserva(int numRef);
	public boolean checkIn(int numRef);
	public boolean checkOut(int numRef);
	
}
