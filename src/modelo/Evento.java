package modelo;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

public class Evento implements Activatable {
String fecha;
String descripcion;
private transient Activator _activator;
public Evento(String fecha, String descripcion) {
	super();
	this.fecha = fecha;
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
public String getDescripcion() {
	activate(ActivationPurpose.READ);
	return descripcion;
}
public void setDescripcion(String descripcion) {
	activate(ActivationPurpose.WRITE);
	this.descripcion = descripcion;
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
