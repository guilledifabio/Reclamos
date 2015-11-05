package modelo;

import java.util.ArrayList;
import java.util.List;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;



public class Ciudadano implements Activatable {
	String nombre;
	String apellido;
	int dni;
	String email;
	int puntos;
	List  canjes;
	private transient Activator _activator;
	
	public Ciudadano(String nombre, String apellido, int dni, String email,
			int puntos) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		this.puntos = puntos;
		this.canjes = new ArrayList();
	}
	public String getNombre() {
		activate(ActivationPurpose.READ);
		return nombre;
	}
	public void setNombre(String nombre) {
		activate(ActivationPurpose.WRITE);
		this.nombre = nombre;
	}
	public String getApellido() {
		activate(ActivationPurpose.READ);
		return apellido;
	}
	public void setApellido(String apellido) {
		activate(ActivationPurpose.WRITE);
		this.apellido = apellido;
	}
	public int getDni() {
		activate(ActivationPurpose.READ);
		return dni;
	}
	public void setDni(int dni) {
		activate(ActivationPurpose.WRITE);
		this.dni = dni;
	}
	public String getEmail() {
		activate(ActivationPurpose.READ);
		return email;
	}
	public void setEmail(String email) {
		activate(ActivationPurpose.WRITE);
		this.email = email;
	}
	public int getPuntos() {
		activate(ActivationPurpose.READ);
		return puntos;
	}
	public void setPuntos(int puntos) {
		activate(ActivationPurpose.WRITE);
		this.puntos = puntos;
	}
	public List getCanjes() {
		activate(ActivationPurpose.READ);
		return canjes;
	}
	public void setCanjes(List canjes) {
		activate(ActivationPurpose.WRITE);
		this.canjes = canjes;
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
