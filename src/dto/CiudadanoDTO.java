package dto;

import java.util.List;

public class CiudadanoDTO {

	private String id = null;
	private String nombre;
	private String apellido;
	private int dni;
	private String email;
	private int puntos;
	private List<CanjeDTO> canjes;
	private List<ReclamoDTO> reclamos;

	public CiudadanoDTO() {
		super();
	}

	public CiudadanoDTO(String id, String nombre, String apellido, int dni, String email, int puntos,
			List<CanjeDTO> canjes, List<ReclamoDTO> reclamos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.puntos = puntos;
		this.canjes = canjes;
		this.reclamos = reclamos;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public List<CanjeDTO> getCanjes() {
		return canjes;
	}

	public void setCanjes(List<CanjeDTO> canjes) {
		this.canjes = canjes;
	}

	public List<ReclamoDTO> getReclamos() {
		return reclamos;
	}

	public void setProductos(List<ReclamoDTO> reclamos) {
		this.reclamos = reclamos;
	}
}
