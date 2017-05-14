package service;

import java.time.LocalDate;
import java.util.HashMap;

import common.Common;
import common.Constants;
import model.Habitacion;
import model.Reserva;

/**
 * 
 * @author alumno
 * 
 *         Servicios que ofrece el propio sistema para gestionar el Hotel
 *
 */
public class HotelServiceFacadeImpl implements HotelServiceFacade {

	public HotelServiceFacadeImpl() {
		super();
	}

	/**
	 * Modifica las tarifas preestablecidas
	 */
	public void modificarTarifa(String tipo, double tarifa) {
		if (tarifa > 0.0) {
			Common.getHotel().getTarifas().put(tipo, tarifa);
			// aquí aplicamos las tarifas nuevas para las habitaciones que ya existen
			for (Habitacion hab : Common.getHotel().getHabitaciones()) {
				if (hab.getClass().getSimpleName().equals(tipo)) {
					hab.setPrecio(tarifa);
				}
			}
		}
	}

	/**
	 * Consultar las ganancias en funci�n del filtro "mes"
	 */
	public double consultarGanancias(String anoMes) {
		double ganancias = 0.0;
		
		try {
			// Parseamos el mes de que calculamos ganancias
			String[] dateParts = anoMes.split("-");
			int anyo = Integer.parseInt(dateParts[0]);
			int mes = Integer.parseInt(dateParts[1]);

			// ponemos el primer día del mes a fecha y después volvemos un día
			// atras para recoger fecha desde primer día del mes necesario
			// usando
			// el metodo isAfter()
			LocalDate date1 = LocalDate.of(anyo, mes, 1).minusDays(1);

			// ponemos el primer día y siguiente mes del nuestro mes necesario,
			// para usar el metodo isBefore()
			if (mes == 12) {
				anyo++;
				mes = 1;
			} else
				mes++;
			LocalDate date2 = LocalDate.of(anyo, mes, 1);

			// recorremos todas las reservas buscando el estado CERRADA y que
			// cumplen la fecha necesidada
			for (Reserva r : Common.getHotel().getReservas()) {
				if ((r.getOut().isAfter(date1)) && (r.getOut().isBefore(date2))
						&& (r.getEstado() == Constants.CERRADA)) {
					// aquí habría que usar el precio no actual, sino el precio
					// de la reserva ya cumpla y cerrada
					// (antes la reserva podría tener diferente precio de
					// actual)
					ganancias = ganancias + r.getNumDias() * r.getHabitacion().getPrecio();
				}
			}
		} catch (Exception ex) {
			System.out.println(">>>>PageController: CalcularGanancias - consultarGanancias EXCEPTION: " + ex.toString());
		}
		return ganancias;
	}

	/**
	 * Consultar precios de las habitaciones
	 */
	public HashMap<String, Double> consultarPrecios() {
		return Common.getHotel().getTarifas();
	}

}
