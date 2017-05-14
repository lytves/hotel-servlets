package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;

import common.Common;
import common.Constants;
import model.Reserva;
import model.Cliente;
import model.Habitacion;
import service.compositeentity.CompositeEntity;


/**
 * 
 * @author alumno
 * 
 * Servicios que ofrece la entidad Reserva para gestionar las reservas de las habitaciones del Hotel
 *
 */
@LocalBean
public class ReservaServiceFacadeImpl implements ReservaServiceFacade {
	private CompositeEntity compositeEntity;

    /**
     * Default constructor. 
     */
	public ReservaServiceFacadeImpl() {
		super();
		compositeEntity = new CompositeEntity();
	}
	
	/**
	 * Reservar una habitaci�n
	 */
	public List<Reserva> reservar(Cliente c, Reserva r, int numhabitacion) {

		//Procesado de datos
		System.out.println(">>>>Reservando Habitacion...");
		
		// se calculan los id en BD 
		r.setId(Common.getHotel().getReservas().size());
		
		// Busca si el cliente existe en BD (puede tener otras reservas)
		int id = buscaCliente(c.getNif());
		
		// Si el cliente NO existe se calcula un ID y se a�ade a la BD
		if(id==0) { 
			c.setId(Common.getHotel().getClientes().size());
			Common.getHotel().getClientes().add(c);
		
		// Si el cliente SI existe se recupera el ID y se anyade 
		} else {
			c.setId(id);
		}
		
		//calcula la fecha de salida 
		r.setOut(r.getIn().plusDays(r.getNumDias()));
		
		// define el estado de la reserva
		r.setEstado(Constants.ACTIVA);
				
		// inserci�n de datos en BD
		for(Habitacion hab : Common.getHotel().getHabitaciones()) {	
			if(hab.getNumero() == numhabitacion) {
				hab.incrementReservas();
				
				compositeEntity = new CompositeEntity(r);
				compositeEntity.setData(hab, c);
				
				break;
			}
		}
					
		return Common.getHotel().getReservas();
	}
	
	/**
	 * Consultar las reservas realizadas por un cliente
	 */
	public List<Reserva> consultarReservas(String nif) {
		List<Reserva> reservaslist = new ArrayList<Reserva>();
		
		for(Reserva r : Common.getHotel().getReservas()) {
			if(r.getCliente().getNif().equals(nif)) {
				reservaslist.add(r);
			}
		}

		return reservaslist;
	}
	
	/**
	 * Cancelar una reserva 
	 */
	public List<Reserva> cancelarReserva(int numRef) {
		
		System.out.println(">>>>Cancelando Reserva...");
		
		// Recorre todas las reservas existentes
		for(Reserva r : Common.getHotel().getReservas()) {
			
			// busca el numero de referencia numRef de la reserva en BD 
			if(r.getId().equals(numRef)) {
			
				// Cambiar el estado de la habitaci�n en BD
				cambiaEstadoHabitacion(r);
				
				// Eliminar la reserva de reservas
//				synchronized (Common.getHotel()){
//					Common.getHotel().getReservas().remove(r);
//				}
				
				// Define el estado de la oferta
				r.setEstado(Constants.CANCELADA);
				
				// Si se cancela sin 2 dias de antelaci�n se cobra la primera noche
		    	if(r.getIn().isAfter(LocalDate.now().minusDays(2))) {
		    		// llamar al servicio de cobro (una noche)
		    		// FIXME
		    		
		    		System.out.println("llamar al servicio de cobro (una noche)");
		    	}
		    	break;
			}
		}
		
		return Common.getHotel().getReservas();
	}
	
	/**
	 * Registrar la entrada de un cliente en funci�n de su reserva
	 */
	public boolean checkIn(int numRef) {
		
		System.out.println(">>>>Realizando CheckIn...");
		
		// Recorre todas las reservas existentes
		for (Reserva r : Common.getHotel().getReservas()) {
			
			// busca el numero de referencia numRef de la reserva en BD 
			if (r.getId().equals(numRef)) {
			
				// Cambiar el estado de la reserva
				r.setEstado(Constants.PROGRESO);
				
		    	break;
			}
		}
		return true;
	}
	
	/**
	 * Registrar la salida de un cliente en funci�n de su reserva
	 */
	public boolean checkOut(int numRef) {
		
		System.out.println(">>>>Realizando CheckOut...");
		
		// Recorre todas las reservas existentes
		for (Reserva r : Common.getHotel().getReservas()) {
			
			// busca el numero de referencia numRef de la reserva en BD 
			if (r.getId().equals(numRef)) {
			
				// Cambiar el estado de la reserva
				r.setEstado(Constants.CERRADA);
	    		// llamar al servicio de cargar a la tarjeta el importe total de la estancia
	    		// FIXME
				
		    	break;
			}
		}
		return true;
	}
	
	
	//Functions y methods
	
	/**
	 * Decrementa el numero de reservas para una Habitaci�n
	 */
	public void cambiaEstadoHabitacion(Reserva r) {
		for(Habitacion habitacion : Common.getHotel().getHabitaciones()) {
			if(habitacion.getId().equals(r.getHabitacion().getId())) {
				habitacion.decrementReservas();
			}
		}
	}
	
	public int buscaCliente(String nif) {
		for(Cliente c : Common.getHotel().getClientes()) {
			if(c.getNif().equals(nif)) {
				return c.getId();
			}
		}
		return 0;
	}
}
