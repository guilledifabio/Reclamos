package dto;

import java.util.ArrayList;
import java.util.List;

public class CatalogoDTO {

	private String id = null;
	private String nombre;
	private List<ProductoDTO> productos;

	public CatalogoDTO() {
		super();
	}
	
	public CatalogoDTO(String nombre) {
		super();
		this.id = null;
		this.nombre = nombre;
		this.productos = new ArrayList<ProductoDTO>();
	}
	
	public CatalogoDTO(String id, String nombre, List<ProductoDTO> productos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.productos = productos;
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

	public List<ProductoDTO> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoDTO> productos) {
		this.productos = productos;
	}
}
