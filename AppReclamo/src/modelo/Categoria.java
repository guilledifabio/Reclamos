package modelo;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

public class Categoria implements Activatable {
	String nombre;
	String descripcion;
	int puntos;
	private transient Activator _activator;
	public Categoria(String nombre, String descripcion, int puntos) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.puntos = puntos;
	}
	public String getNombre() {
		activate(ActivationPurpose.READ);
		return nombre;
	}
	public void setNombre(String nombre) {
		activate(ActivationPurpose.WRITE);
		this.nombre = nombre;
	}
	public String getDescripcion() {
		activate(ActivationPurpose.READ);
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		activate(ActivationPurpose.WRITE);
		this.descripcion = descripcion;
	}
	public int getPuntos() {
		activate(ActivationPurpose.READ);
		return puntos;
	}
	public void setPuntos(int puntos) {
		activate(ActivationPurpose.WRITE);
		this.puntos = puntos;
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
