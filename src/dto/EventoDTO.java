package dto;

import java.time.LocalDateTime;

public class EventoDTO {

	private String id;
	private LocalDateTime fecha;
	private String descripcion;

	public EventoDTO() {
		super();
	}

	public EventoDTO(String id, LocalDateTime fecha, String descripcion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.descripcion = descripcion;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
