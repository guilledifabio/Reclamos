package dto;

import java.time.LocalDateTime;

public class CanjeDTO {

	private String id;
	private LocalDateTime fecha;
	private ProductoDTO producto;

	public CanjeDTO() {
		super();
	}
	
	public CanjeDTO(String id, LocalDateTime fecha, ProductoDTO producto) {
		super();
		this.id = id;
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

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}
}
