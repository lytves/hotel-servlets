package service;

import java.util.List;

import model.Habitacion;
import model.Reserva;

public interface HabitacionServiceFacade {
	public List<Habitacion> consultarHabitacinesDisponibles(String tipoHabitacion, 
			String fechaEntrada_dia, String fechaEntrada_mes, String fechaEntrada_any, String numDias);
	public List<Reserva> consultarHabitacinesReservadas();

}
