package modelo;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

public class Producto implements Activatable {
	
	String nombre;
	int puntosrequeridos;
	private transient Activator _activator;

	public Producto(String nombre, int puntosrequeridos) {
		super();
		
		this.nombre = nombre;
		this.puntosrequeridos = puntosrequeridos;
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


	@Override
	public String toString() {
		return " Producto [nombre=" + nombre + ", puntosrequeridos=" + puntosrequeridos + "]";
	}

}
