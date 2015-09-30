package modelo;

import java.util.ArrayList;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

public class Catalogo implements Activatable {
String nombre;
ArrayList<Producto> productos;
private transient Activator _activator;

public Catalogo(String nombre) {
	super();
	this.nombre = nombre;
	this.productos = null;
}
public String getNombre() {
	activate(ActivationPurpose.READ);
	return nombre;
}
public void setNombre(String nombre) {
	activate(ActivationPurpose.WRITE);
	this.nombre = nombre;
}
public ArrayList<Producto> getProductos() {
	activate(ActivationPurpose.READ);
	return productos;
}
public void setProductos(ArrayList<Producto> productos) {
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
