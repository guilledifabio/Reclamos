package modelo;

import java.time.LocalDateTime;
import java.util.UUID;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

import dao.EventoDaoImpl;
import dto.EventoDTO;

public class Evento implements Activatable {
	
	private String id = null;
	private LocalDateTime fecha;
	private String descripcion;
	private transient Activator _activator;

	public Evento(String id, LocalDateTime fecha, String descripcion) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.descripcion = descripcion;

	}

	public Evento(String descripcion) {
		super();
		LocalDateTime dia = LocalDateTime.now();
		this.id = UUID.randomUUID().toString();

		this.fecha = dia;
		this.descripcion = descripcion;

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
	
	public EventoDTO toDTO() {
		EventoDTO eventoDto = new EventoDTO();
		eventoDto.setId(this.id);
		eventoDto.setFecha(this.fecha);
		eventoDto.setDescripcion(this.descripcion);
		
		return eventoDto;
	}
}
