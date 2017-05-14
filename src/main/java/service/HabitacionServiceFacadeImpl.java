package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import common.Common;
import common.Constants;
import model.Habitacion;
import model.Reserva;

/**
 * 
 * @author alumno
 * 
 * Servicios que ofrece la entidad Habitación para gestionar las habitaciones del Hotel
 *
 */
public class HabitacionServiceFacadeImpl implements HabitacionServiceFacade {
	
	public HabitacionServiceFacadeImpl() {
		super();
	}
	
	/**
	 * Consultar habitaciones del Hotel en función de los filtros establecidos
	 */
	public List<Habitacion> consultarHabitacinesDisponibles(String tipoHabitacion, 
			String fechaEntrada_dia, String fechaEntrada_mes, String fechaEntrada_any, String numDias) {
		
		List<Habitacion> habitacioneslist = new ArrayList<Habitacion>();
		LocalDate fechaIn = null;
		LocalDate fechaOut = null;
		Integer diasEstancia = 0;
		Boolean filtro = false;	// Establece que el filtro es nulo
		Boolean disponible = false;	// Establece que el estado es NO disponible

		// Parsea los Filtros al TipoDato correspondiente
		if(!tipoHabitacion.equals("")) {
			filtro = true;
		}
		
		if(!fechaEntrada_dia.equals("") && !fechaEntrada_mes.equals("") && !fechaEntrada_any.equals("") && !numDias.equals("")){ 
			fechaIn = LocalDate.of(Integer.parseInt(fechaEntrada_any), 
					Integer.parseInt(fechaEntrada_mes), Integer.parseInt(fechaEntrada_dia));

			diasEstancia = Integer.parseInt(numDias);
			fechaOut = fechaIn.plusDays(diasEstancia);
			filtro = true;
			
		}
	
		/* Si hay filtros establecidos, ademas se recogen las habitaciones disponibles de acuerdo a los filtros */
		if(filtro) {
			// Para cada habitación...
			for(Habitacion hab : Common.getHotel().getHabitaciones()) {
				
				// Si está disponible la añade directamente a la lista
				if(hab.getEstado().equals(Constants.DISPONIBLE)) {
					if(tipoHabitacion != "" || tipoHabitacion != null) {
						if(tipoHabitacion.equals(hab.getClass().getSimpleName())) {
							habitacioneslist.add(hab);
						}
					} else {
						habitacioneslist.add(hab);
					}

				// Si NO está disponible recorre las reservas aplicando los filtros
				} else {
					// Para cada reserva activa...
					for(Reserva r : Common.getHotel().getReservas()) {
						disponible = false;
						if(r.getEstado().equals(Constants.ACTIVA)) {
							
							// si el filtro "tipoHabitación" está establecido, lo aplica
							if(!tipoHabitacion.equals("")) {
								if(tipoHabitacion.equals(r.getHabitacion().getClass().getSimpleName())) {
									disponible = true;
									
									// aplica el filtro para las busquedas de habitaciones libres entre dos fechas
									if(!consultarDisponibilidadFechas(fechaIn, fechaOut, r.getIn(), r.getOut())){
										disponible = false;
									}
								}
							// Aplica solo el filtro fecha, pues el filtro tipoHabitacion NO está establecido
							} else if(consultarDisponibilidadFechas(fechaIn, fechaOut, r.getIn(), r.getOut())){
								disponible = true;
								
							} else {
								// No esta disponible para los filtros establecidos
								disponible = false;
	
							}
							
							// La habitación SI esta disponible para los filtros establecidos
							if(disponible) {
								Boolean existe = false;
								// Comprueba que la habiración no esté ya en BD
								for(Habitacion habisDisponibles : habitacioneslist) {
									existe= false;
									if(habisDisponibles.getId().equals(r.getHabitacion().getId())) { 
										existe=true;
										break;
									}
								}
								if(!existe) habitacioneslist.add(r.getHabitacion());
							}
						}
					}
				}
			}
		} else {
			System.out.println("directe");
			
			/* Se recogen todas las habitaciones disponibles */
			for(Habitacion hab : Common.getHotel().getHabitaciones()) {
				
				// Añade la tación disponible a la lista
				if(hab.getEstado().equals(Constants.DISPONIBLE)) {
					habitacioneslist.add(hab);
				}
			}
		}
		
		return habitacioneslist;
	}

	public List<Reserva> consultarHabitacinesReservadas() {
		List<Reserva> reservalist = new ArrayList<Reserva>();
		for(Reserva r : Common.getHotel().getReservas()) {
			if(r.getEstado().equals(Constants.ACTIVA)) {
				reservalist.add(r);
			}
		}
		return reservalist;
	}
	
	public boolean consultarDisponibilidadFechas(LocalDate fechaIn, LocalDate fechaOut, LocalDate fIn, LocalDate fOut) {
		// [ FechaEntrada < fechaIn and fechaOut  or FechaEntrada > fechaOut ]
		if((fechaIn != null && fechaOut != null) && (
				//((fechaIn.isBefore(fIn) || !fechaIn.isEqual(fIn)) && (fechaOut.isBefore(fIn) || !fechaOut.isEqual(fIn))) || 
				(fechaIn.isBefore(fIn) && (fechaOut.isBefore(fIn))) ||
				(fechaIn.isAfter(fOut)) )){
			
			return true;
		} else {
			return false;
		}
	}
	
	//...
}
