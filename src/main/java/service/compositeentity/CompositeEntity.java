package service.compositeentity;

import model.Cliente;
import model.Habitacion;
import model.Reserva;

/**
 * Session Bean implementation class CompositeEntity
 */
public class CompositeEntity {
	private CoarseGrainedObject cgo = new CoarseGrainedObject();
	
	public CompositeEntity() {
		cgo = new CoarseGrainedObject();
	}
	
	public CompositeEntity(Reserva r) {
		cgo = new CoarseGrainedObject(r);
	}

	public void setData(Habitacion data1, Cliente data2){
		cgo.setData(data1, data2);
	}

	public Object[] getData(){
		return cgo.getData();
	}
}
