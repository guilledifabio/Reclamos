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
		this.id = UUID.randomUUID().toString();
		this.fecha = LocalDateTime.now();
		this.descripcion = descripcion;

	}
	
	public Evento(EventoDTO eventoDto) {
		super();
		this.id = UUID.randomUUID().toString();
		this.fecha = LocalDateTime.now();
		this.descripcion = eventoDto.getDescripcion();
	}
	
	public String getId() {
		activate(ActivationPurpose.READ);
		return id;
	}

	public void setId(String id) {
		activate(ActivationPurpose.WRITE);
		this.id = id;
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

	public String toString() {
		String string = "Fecha: " + getFecha().toString() + " - " + getDescripcion();
		return string;
	}

	public EventoDTO toDTO() {
		EventoDTO eventoDto = new EventoDTO();
		eventoDto.setId(getId());
		eventoDto.setFecha(getFecha());
		eventoDto.setDescripcion(getDescripcion());

		return eventoDto;
	}
}
