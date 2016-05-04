package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

public class Canje implements Activatable {
	Date fecha;
	Producto producto;
	private transient Activator _activator;

	public Canje(String fecha) {
		super();
		try {
			SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
			Date dia = formateador.parse(fecha);

			this.fecha = dia;
			this.producto = null;
		} catch (ParseException e) {
			System.out.println("Se Produjo un Error!!!  " + e.getMessage());
		}
	}

	

	public Date getFecha() {
		activate(ActivationPurpose.READ);
		return fecha;
	}

	public void setFecha(Date fecha) {
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
		return "Canje [fecha= " + fecha + "," + producto + "]";
	}

}
