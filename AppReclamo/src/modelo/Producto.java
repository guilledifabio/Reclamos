package modelo;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

public class Producto implements Activatable {
	int id;
	String nombre;
	int puntosrequeridos;
	private transient Activator _activator;

	public Producto(int id, String nombre, int puntosrequeridos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.puntosrequeridos = puntosrequeridos;
	}

	public int getId() {
		activate(ActivationPurpose.READ);
		return id;
	}

	public void setId(int id) {
		activate(ActivationPurpose.WRITE);
		this.id = id;
	}

	public String getNombre() {
		activate(ActivationPurpose.READ);
		return nombre;
	}

	public void setNombre(String nombre) {
		activate(ActivationPurpose.WRITE);
		this.nombre = nombre;
	}

	public int getPuntosrequeridos() {
		activate(ActivationPurpose.READ);
		return puntosrequeridos;
	}

	public void setPuntosrequeridos(int puntosrequeridos) {
		activate(ActivationPurpose.WRITE);
		this.puntosrequeridos = puntosrequeridos;
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

}
