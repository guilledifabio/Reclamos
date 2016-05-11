package dto;

public class ProductoDTO {

	private String id;
	private String nombre;
	private int puntosrequeridos;

	public ProductoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductoDTO(String id, String nombre, int puntosrequeridos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.puntosrequeridos = puntosrequeridos;
	}
	public ProductoDTO(String nombre, int puntosrequeridos) {
		super();
		this.id = null;
		this.nombre = nombre;
		this.puntosrequeridos = puntosrequeridos;
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

	public int getPuntosrequeridos() {
		return puntosrequeridos;
	}

	public void setPuntosrequeridos(int puntosrequeridos) {
		this.puntosrequeridos = puntosrequeridos;
	}
}
