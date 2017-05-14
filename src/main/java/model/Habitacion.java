package model;

import common.Constants;

public abstract class Habitacion {
	private Integer id;
	private Integer numero;
	private double precio;
	private int reservas=0;
	private String estado;

	public Habitacion(int id, int numero, double precio,  int reservas) {
		super();
		this.id = id;
		this.numero = numero;
		this.precio = precio;
		this.reservas = reservas;
	}


	// getters y setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getReservas() {
		return reservas;
	}

	public void setReservas(int reservas) {
		this.reservas = reservas;
	}

	
	// Functions
	public String getEstado() {
		estado = Constants.UNDEFINED;
	
		if(reservas > 0) {
			estado = Constants.RESERVADA;
		} else {
			estado = Constants.DISPONIBLE;
		}
		
		return estado;
	}
	
	public void incrementReservas() {
		reservas ++;
	}
	public void decrementReservas() {
		reservas --;
	}
	
}
