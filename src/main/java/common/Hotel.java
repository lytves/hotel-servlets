package common;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Cliente;
import model.Doble;
import model.Habitacion;
import model.Matrimonio;
import model.Reserva;
import model.Simple;

/**
 * Inicializa el hotel: carga de datos
 */
public class Hotel {
	private List<Cliente> clientes;
	private List<Reserva> reservas;
	private List<Habitacion> habitaciones;
	private HashMap<String, Double> tarifas;

	public void init() {
		System.out.println("Inicializando...");
		
		tarifas = new HashMap<String, Double>(3);
		clientes = new ArrayList<Cliente>();
		reservas = new ArrayList<Reserva>();
		habitaciones = new ArrayList<Habitacion>();

		cargarTarifas(tarifas);
		cargarClientes(clientes);
		cargarHabitaciones(habitaciones, tarifas);
		cargarReservas(reservas, habitaciones, clientes);
		
		System.out.println("Inicializado.");
	}

	private void cargarTarifas(HashMap<String, Double> t) {
		t.put(Constants.SIMPLE, Constants.TARIFA_SIMPLE);
		t.put(Constants.DOBLE, Constants.TARIFA_DOBLE);
		t.put(Constants.MATRIMONIO, Constants.TARIFA_MATRIMONIO);
	}
	
	public void cargarClientes(List<Cliente> c ) {
		// Cargar clientes de un fichero
		
		c.add(new Cliente(1, "12345678A", "Vadym", "Lytvynenko"));
		c.add(new Cliente(2, "91011121B","Dario", "Torres"));
		c.add(new Cliente(3, "31415161C","Antoni", "Castello"));
		c.add(new Cliente(4, "71819201D","Ignacio", "Panach"));
		c.add(new Cliente(5, "12223242E", "Enrique", "Bonet"));
		c.add(new Cliente(6, "72365678F", "Ramon", "Cirilo"));
		c.add(new Cliente(7, "09897349G", "Juan", "DeMata"));
		c.add(new Cliente(8, "98745489H", "Francisco", "Garcia"));
		c.add(new Cliente(9, "64734788I", "Juan", "Gutierrez"));
		c.add(new Cliente(10, "56483296J", "Jesus", "Albert"));
		c.add(new Cliente(11, "23649327K", "Imma", "Coma"));
		
	}
	
	public void cargarReservas(List<Reserva> r, List<Habitacion> h, List<Cliente> c) {
		// Cargar reservas de un fichero
		// Reserva(id, in, out, numDias, estado, habitacion, cliente) {
		r.add(new Reserva(1,  LocalDate.of(2016, 12, 10), LocalDate.of(2016, 12, 20), 10, Constants.CERRADA,   h.get(0),  c.get(1)));
		r.add(new Reserva(2,  LocalDate.of(2017, 2, 15),  LocalDate.of(2017, 2, 28), 13,  Constants.ACTIVA,    h.get(1),  c.get(2)));
		r.add(new Reserva(3,  LocalDate.of(2017, 3, 15),  LocalDate.of(2017, 3, 30), 15,  Constants.ACTIVA,    h.get(5),  c.get(3)));
		r.add(new Reserva(4,  LocalDate.of(2017, 1, 19),  LocalDate.of(2017, 1, 30), 11,  Constants.CERRADA,   h.get(6),  c.get(4)));
		r.add(new Reserva(5,  LocalDate.of(2017, 5, 2),   LocalDate.of(2017, 5, 20), 18,  Constants.ACTIVA,    h.get(7),  c.get(5)));
		r.add(new Reserva(6,  LocalDate.of(2017, 5, 10),  LocalDate.of(2017, 5, 15), 5,   Constants.ACTIVA,    h.get(13), c.get(6)));
		r.add(new Reserva(7,  LocalDate.of(2017, 6, 2),   LocalDate.of(2017, 6, 10), 8,   Constants.ACTIVA,    h.get(14), c.get(7)));
		r.add(new Reserva(8,  LocalDate.of(2017, 1, 1),   LocalDate.of(2017, 1, 12), 12,  Constants.CERRADA,   h.get(15), c.get(8)));
		r.add(new Reserva(9,  LocalDate.of(2017, 7, 1),   LocalDate.of(2017, 7, 10), 10,  Constants.ACTIVA,    h.get(16), c.get(9)));
		r.add(new Reserva(10, LocalDate.of(2017, 7, 15),  LocalDate.of(2017, 7, 30), 15,  Constants.ACTIVA,    h.get(17), c.get(0)));
		r.add(new Reserva(11, LocalDate.of(2017, 7, 20),  LocalDate.of(2017, 7, 29), 9,   Constants.PROGRESO,  h.get(7),  c.get(0)));
		r.add(new Reserva(12, LocalDate.of(2017, 1, 1),   LocalDate.of(2017, 1, 10), 10,  Constants.CERRADA,   h.get(1),  c.get(0)));
		r.add(new Reserva(13, LocalDate.of(2017, 10, 10), LocalDate.of(2017, 10, 15), 5,  Constants.CANCELADA, h.get(3),  c.get(0)));
		r.add(new Reserva(14, LocalDate.of(2017, 8, 2),   LocalDate.of(2017, 8, 19), 17,  Constants.ACTIVA,    h.get(18), c.get(8)));
		r.add(new Reserva(15, LocalDate.of(2017, 8, 8),   LocalDate.of(2017, 8, 12), 4,   Constants.ACTIVA,    h.get(22), c.get(0)));
		r.add(new Reserva(16, LocalDate.of(2016, 12, 8),  LocalDate.of(2016, 12, 12), 4,  Constants.CERRADA,   h.get(2),  c.get(1)));
		r.add(new Reserva(17, LocalDate.of(2016, 8, 8),   LocalDate.of(2016, 8, 12), 4,   Constants.CERRADA,   h.get(3),  c.get(3)));
		r.add(new Reserva(18, LocalDate.of(2016, 9, 1),   LocalDate.of(2016, 9, 10), 9,   Constants.CERRADA,   h.get(5),  c.get(0)));
		r.add(new Reserva(19, LocalDate.of(2016, 11, 2),  LocalDate.of(2016, 11, 12), 10, Constants.CERRADA,   h.get(6),  c.get(2)));
		r.add(new Reserva(20, LocalDate.of(2016, 12, 20), LocalDate.of(2016, 12, 22), 2,  Constants.CERRADA,   h.get(1),  c.get(5)));
		r.add(new Reserva(21, LocalDate.of(2016, 10, 3),  LocalDate.of(2016, 10, 12), 9,  Constants.CERRADA,   h.get(11), c.get(0)));
		r.add(new Reserva(22, LocalDate.of(2016, 7, 5),   LocalDate.of(2016, 7, 18), 13,  Constants.CERRADA,   h.get(14), c.get(1)));
		r.add(new Reserva(23, LocalDate.of(2016, 6, 4),   LocalDate.of(2016, 6, 19), 15,  Constants.CERRADA,   h.get(17), c.get(6)));
		r.add(new Reserva(24, LocalDate.of(2016, 4, 8),   LocalDate.of(2016, 4, 12), 4,   Constants.CERRADA,   h.get(21), c.get(7)));
		r.add(new Reserva(25, LocalDate.of(2016, 11, 22), LocalDate.of(2016, 11, 29), 7,  Constants.CERRADA,   h.get(8),  c.get(0)));
		r.add(new Reserva(26, LocalDate.of(2016, 8, 20),  LocalDate.of(2016, 8, 26), 6,   Constants.CERRADA,   h.get(9),  c.get(1)));
		r.add(new Reserva(27, LocalDate.of(2016, 10, 19), LocalDate.of(2016, 10, 27), 8,  Constants.CERRADA,   h.get(10), c.get(8)));
		r.add(new Reserva(28, LocalDate.of(2016, 9, 16),  LocalDate.of(2016, 9, 23), 7,   Constants.CERRADA,   h.get(11), c.get(0)));
		r.add(new Reserva(29, LocalDate.of(2016, 12, 13), LocalDate.of(2016, 12, 17), 4,  Constants.CERRADA,   h.get(12), c.get(9)));
		r.add(new Reserva(30, LocalDate.of(2016, 11, 2),  LocalDate.of(2016, 11, 12), 10, Constants.CERRADA,   h.get(8),  c.get(1)));
		r.add(new Reserva(31, LocalDate.of(2016, 8, 20),  LocalDate.of(2016, 8, 26), 6,   Constants.CERRADA,   h.get(2),  c.get(10)));
		r.add(new Reserva(32, LocalDate.of(2016, 10, 4),  LocalDate.of(2016, 10, 12), 8,  Constants.CERRADA,   h.get(20), c.get(5)));
		r.add(new Reserva(33, LocalDate.of(2016, 7, 25),  LocalDate.of(2016, 7, 30), 5,   Constants.CERRADA,   h.get(6),  c.get(6)));
		r.add(new Reserva(34, LocalDate.of(2016, 5, 28),  LocalDate.of(2016, 5, 31), 3,   Constants.CERRADA,   h.get(7),  c.get(7)));
		
	}
	
	public void cargarHabitaciones(List<Habitacion> h, HashMap<String, Double> t) {
		// Cargar habitaciones de un fichero (id, número, precio, reservas)
		
		h.add(new Simple(1, 100, t.get(Constants.SIMPLE), 1));
		h.add(new Doble(2, 101, t.get(Constants.DOBLE), 1));
		h.add(new Matrimonio(3, 102, t.get(Constants.MATRIMONIO), 0));
		h.add(new Simple(4, 103, t.get(Constants.SIMPLE), 0));
		h.add(new Doble(5, 104, t.get(Constants.DOBLE), 0));
		h.add(new Matrimonio(6, 105, t.get(Constants.MATRIMONIO), 1));
		h.add(new Simple(7, 106, t.get(Constants.SIMPLE), 1));
		h.add(new Doble(8, 107, t.get(Constants.DOBLE), 1));
		h.add(new Matrimonio(9, 108, t.get(Constants.MATRIMONIO), 0));
		h.add(new Simple(10, 109, t.get(Constants.SIMPLE), 0));
		h.add(new Doble(11, 110, t.get(Constants.DOBLE), 0));
		h.add(new Matrimonio(12, 111, t.get(Constants.MATRIMONIO), 0));
		h.add(new Simple(13, 112, t.get(Constants.SIMPLE), 0));
		h.add(new Doble(14, 113, t.get(Constants.DOBLE), 1));
		h.add(new Matrimonio(15, 114, t.get(Constants.MATRIMONIO), 1));
		h.add(new Simple(16, 115, t.get(Constants.SIMPLE), 1));
		h.add(new Doble(17, 116, t.get(Constants.DOBLE), 1));
		h.add(new Matrimonio(18, 117, t.get(Constants.MATRIMONIO), 1));
		h.add(new Simple(19, 118, t.get(Constants.SIMPLE), 1));
		h.add(new Doble(20, 119, t.get(Constants.DOBLE), 0));
		h.add(new Matrimonio(21, 120, t.get(Constants.MATRIMONIO), 0));
		h.add(new Simple(22, 121, t.get(Constants.SIMPLE), 0));
		h.add(new Doble(23, 122, t.get(Constants.DOBLE), 1));
		h.add(new Matrimonio(24, 123, t.get(Constants.MATRIMONIO), 0));
		
	}
	
	public void destroy() {
		clientes = null;
		reservas = null;
		habitaciones = null;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<Habitacion> getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	
	public HashMap<String, Double> getTarifas() {
		return tarifas;
	}

	
	public void setTarifas(HashMap<String, Double> tarifas) {
		this.tarifas = tarifas;
	}
	
}
