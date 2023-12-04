package ar.com.codoacodo.entity;

public class OradorRequest {
	
	private Long id;
	private String nombre;
	private String apellido;
	private String mail;
	private String tema;	
	
	public OradorRequest() {
		
	}

	public OradorRequest(String nombre, String apellido, String mail, String tema) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.tema = tema;
	}
	
	public OradorRequest(Long id,String nombre, String apellido, String mail, String tema) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.tema = tema;
	}
	
	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getMail() {
		return mail;
	}

	public String getTema() {
		return tema;
	}

	@Override
	public String toString() {
		return "OradorRequest [nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail + ", tema=" + tema + "]";
	}

}
