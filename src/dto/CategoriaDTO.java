package dto;

public class CategoriaDTO {

	private String id;
	private String nombre;
	private String descripcion;
	private int puntos;

	public CategoriaDTO() {
		super();
	}

	public CategoriaDTO(String nombre, String descripcion, int puntos) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.puntos = puntos;
	}
	
	public CategoriaDTO(String id, String nombre, String descripcion, int puntos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.puntos = puntos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

}
