package dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReclamoDTO {

	private String id = null;
	private LocalDateTime fecha;
	private String descripcion;
	private String direccion;
	private CategoriaDTO categoria;
	private List<EventoDTO> eventos;

	public ReclamoDTO() {
		super();
	}
	
	public ReclamoDTO(String descripcion, String direccion, CategoriaDTO categoria) {
		super();
		this.id = null;
		this.fecha = null;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.categoria = categoria;
		this.eventos = null;
	}
	
	public ReclamoDTO(String id, LocalDateTime fecha, String descripcion, String direccion, CategoriaDTO categoria,
			List<EventoDTO> eventos) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.categoria = categoria;
		this.eventos = eventos;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	public List<EventoDTO> getEventos() {
		return eventos;
	}

	public void setEventos(List<EventoDTO> eventos) {
		this.eventos = eventos;
	}
}
