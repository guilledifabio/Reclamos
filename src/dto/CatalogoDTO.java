package dto;

import java.util.ArrayList;
import java.util.List;

import modelo.Producto;

public class CatalogoDTO {

	private String id = null;
	private String nombre;
	private List<Producto> productos;

	public CatalogoDTO() {
		super();
	}

	public CatalogoDTO(String id, String nombre, List<Producto> productos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.productos = productos;
	}

	public CatalogoDTO(String nombre) {
		super();
		this.id = null;
		this.nombre = nombre;
		this.productos = new ArrayList<Producto>();
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

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
}
