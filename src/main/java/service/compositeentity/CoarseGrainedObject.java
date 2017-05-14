package service.compositeentity;

import common.Common;
import model.Cliente;
import model.Habitacion;
import model.Reserva;

/**
 * Session Bean implementation class CoarseGrainedObject
 */
public class CoarseGrainedObject {
	Reserva reserva;
	
	public CoarseGrainedObject() {
		reserva = new Reserva();
	}
	
	public CoarseGrainedObject(Reserva r) {
		reserva = r;
	}

	public void setData(Habitacion h, Cliente c){
		reserva.setHabitacion(h);
		reserva.setCliente(c);
		
		Common.getHotel().getReservas().add(reserva);
	}

	public Object[] getData(){
	     return new Object[] {reserva.getHabitacion(),reserva.getCliente()};
	}
}