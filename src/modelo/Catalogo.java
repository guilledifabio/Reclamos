package modelo;

import java.util.ArrayList;
import java.util.List;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

public class Catalogo implements Activatable {
String nombre;
List productos;
private transient Activator _activator;

public Catalogo(String nombre) {
	super();
	this.nombre = nombre;
	this.productos = new ArrayList();
}
public String getNombre() {
	activate(ActivationPurpose.READ);
	return nombre;
}
public void setNombre(String nombre) {
	activate(ActivationPurpose.WRITE);
	this.nombre = nombre;
}
public List getProductos() {
	activate(ActivationPurpose.READ);
	return productos;
}
public void setProductos(List productos) {
	activate(ActivationPurpose.WRITE);
	this.productos = productos;
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
