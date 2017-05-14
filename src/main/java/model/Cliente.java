package model;

public class Cliente {
	private Integer id;
	private String nif;
	private String name;
	private String surname;
	
	
	/**
	 * Constructor parametrizado 
	 */
	public Cliente(Integer id, String nif, String name, String surname) {
		super();
		this.id = id;
		this.nif = nif;
		this.name = name;
		this.surname = surname;
	}
	
	
	
	/**
	 *  getters y setters
	 */
	
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

}
