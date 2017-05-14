package model;

import java.time.LocalDate;

public class Reserva {
	private Integer id;
	private LocalDate in;
	private LocalDate out;
	private Integer numDias;
	private String estado;
	private Habitacion habitacion;
	private Cliente cliente;
	//...
	
	public Reserva(){}
	public Reserva(Integer id, LocalDate in, LocalDate out, Integer numDias, String estado, Habitacion habitacion, Cliente cliente) {
		super();
		this.id = id;
		this.in = in;
		this.out = out;
		this.numDias = numDias;
		this.estado = estado;
		this.habitacion = habitacion;
		this.cliente = cliente;
	}

	// getters y setters
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDate getIn() {
		return in;
	}
	public void setIn(LocalDate in) {
		this.in = in;
	}
	public LocalDate getOut() {
		return out;
	}
	public void setOut(LocalDate out) {
		this.out = out;
	}

	public Integer getNumDias() {
		return numDias;
	}
	public void setNumDias(Integer numDias) {
		this.numDias = numDias;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
