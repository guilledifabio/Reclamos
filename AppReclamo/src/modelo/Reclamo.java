package modelo;

import java.util.ArrayList;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

public class Reclamo implements Activatable {
	String fecha;
	String descripcion;
	String direccion;
	Categoria categoria;
	ArrayList<Evento> eventos;
	private transient Activator _activator;

public Reclamo(String fecha, String descripcion, String direccion) {
		super();
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.categoria = null;
		this.eventos = null;
	}

	public ArrayList<Evento> getEventos() {
		activate(ActivationPurpose.READ);
		return eventos;
	}

	public void setEventos(ArrayList<Evento> eventos) {
		activate(ActivationPurpose.WRITE);
		this.eventos = eventos;
	}

	public String getFecha() {
		activate(ActivationPurpose.READ);
		return fecha;
	}

	public void setFecha(String fecha) {
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
		if(_activator != null) {
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

}
