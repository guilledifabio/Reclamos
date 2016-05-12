package modelo;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

import dto.EventoDTO;
import dto.ReclamoDTO;

public class Reclamo implements Activatable {
	
	private String id = null;
	private LocalDateTime fecha;
	private String descripcion;
	private String direccion;
	private Categoria categoria;
	private List<Evento> eventos;
	private transient Activator _activator;

	public Reclamo(String descripcion, String direccion, Categoria categoria) {
		super();
		this.id = UUID.randomUUID().toString();
		this.fecha = LocalDateTime.now();
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.categoria = categoria;
		this.eventos = new ArrayList<Evento>();
		this.eventos.add(new Evento("El reclamo fue ingresado"));
	}

	public Reclamo(String id, String descripcion, String direccion, Categoria categoria) {
		super();
		this.id = id;
		this.fecha = LocalDateTime.now();
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.categoria = categoria;
		this.eventos = new ArrayList<Evento>();
		this.eventos.add(new Evento("El reclamo fue ingresado"));
	}
	
	public Reclamo(ReclamoDTO reclamoDto, Categoria categoria) {
		this.id = UUID.randomUUID().toString();
		this.fecha = LocalDateTime.now();
		this.descripcion = reclamoDto.getDescripcion();
		this.direccion = reclamoDto.getDireccion();
		this.categoria = categoria;
		this.eventos = new ArrayList<Evento>();
		this.eventos.add(new Evento("El reclamo fue ingresado"));
	}
	
	public String getId() {
		activate(ActivationPurpose.READ);
		return id;
	}

	public void setId(String id) {
		activate(ActivationPurpose.WRITE);
		this.id = id;
	}
	
	public List<Evento> getEventos() {
		activate(ActivationPurpose.READ);
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		activate(ActivationPurpose.WRITE);
		this.eventos = eventos;
	}

	public LocalDateTime getFecha() {
		activate(ActivationPurpose.READ);
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		activate(ActivationPurpose.WRITE);
		this.fecha = fecha;
	}

	public String getDescripcion() {
		activate(ActivationPurpose.READ);
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		activate(ActivationPurpose.WRITE);
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		activate(ActivationPurpose.READ);
		return direccion;
	}

	public void setDireccion(String direccion) {
		activate(ActivationPurpose.WRITE);
		this.direccion = direccion;
	}

	public Categoria getCategoria() {
		activate(ActivationPurpose.READ);
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		activate(ActivationPurpose.WRITE);
		this.categoria = categoria;
	}

	public void activate(ActivationPurpose purpose) {
		if (_activator != null) {
			_activator.activate(purpose);
		}
	}

	public void bind(Activator activator) {
		if (_activator == activator) {
			return;
		}
		if (activator != null && _activator != null) {
			throw new IllegalStateException();
		}
		_activator = activator;
	}

	public void ActualizarPuntos(Ciudadano ciu) {
		int puntos = ciu.getPuntos();

		ciu.setPuntos(puntos + this.categoria.getPuntos());

	}
	
	@Override
	public String toString() {
		String reclamo = " Categoria: " + this.getCategoria().toString() + " - Direccion: " + getDescripcion();
		int i = 1;
		for (Evento evento : eventos) {
			reclamo += "\n "+ i++ + " - " + evento.toString();
		}
		return reclamo;
	}
	
	public ReclamoDTO toDTO() {
		ReclamoDTO reclamoDto = new ReclamoDTO();
		reclamoDto.setId(this.id);
		reclamoDto.setFecha(this.fecha);
		reclamoDto.setDireccion(this.direccion);
		reclamoDto.setCategoria(this.categoria.toDTO());
		List<EventoDTO> levento = new ArrayList<EventoDTO>();
		for (Evento evento : eventos) {
			levento.add(evento.toDTO());
		}
		
		return reclamoDto;
	}
}
