package modelo;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

public class Canje implements Activatable{
	String descripcion;
	String fecha;
	Producto producto;
	private transient Activator _activator;
	public Canje(String descripcion, String fecha, Producto producto) {
		super();
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.producto = producto;
	}
	
	public String getDescripcion() {
		activate(ActivationPurpose.READ);
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		activate(ActivationPurpose.WRITE);
		this.descripcion = descripcion;
	}

	public String getFecha() {
		activate(ActivationPurpose.READ);
		return fecha;
	}

	public void setFecha(String fecha) {
		activate(ActivationPurpose.WRITE);
		this.fecha = fecha;
	}

	public Producto getProducto() {
		activate(ActivationPurpose.READ);
		return producto;
	}

	public void setProducto(Producto producto) {
		activate(ActivationPurpose.WRITE);
		this.producto = producto;
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
