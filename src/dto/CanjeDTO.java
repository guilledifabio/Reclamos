package dto;

import java.time.LocalDateTime;

import modelo.Producto;

public class CanjeDTO {

	private String id;
	private LocalDateTime fecha;
	private Producto producto;

	public CanjeDTO() {
		super();
	}

	public CanjeDTO(String id, LocalDateTime fecha, Producto producto) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.producto = producto;
	}
	public CanjeDTO( LocalDateTime fecha, Producto producto) {
		super();
		this.id = null;
		this.fecha = fecha;
		this.producto = producto;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
