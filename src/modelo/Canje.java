package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

import dto.CanjeDTO;

public class Canje implements Activatable {
	
	private String id = null;
	private LocalDateTime fecha;
	private Producto producto;
	private transient Activator _activator;

	public Canje(LocalDateTime dia) {
		super();

		this.id = UUID.randomUUID().toString();
		this.fecha = dia;
		this.producto = null;
	}

	public Canje() {
		super();
		LocalDateTime dia = LocalDateTime.now();
		this.id = UUID.randomUUID().toString();
		this.fecha = dia;
		this.producto = null;
	}
	
	public Canje(CanjeDTO canjeDto) {
		this.id = UUID.randomUUID().toString();
		this.fecha = canjeDto.getFecha();
		this.producto = null;
	}
	
	public Canje(CanjeDTO canjeDto, Producto producto) {
		this.id = UUID.randomUUID().toString();
		this.fecha = canjeDto.getFecha();
		this.producto = producto;
	}
	
	public String getId() {
		activate(ActivationPurpose.READ);
		return id;
	}

	public void setId(String id) {
		activate(ActivationPurpose.WRITE);
		this.id = id;
	}
	
	public LocalDateTime getFecha() {
		activate(ActivationPurpose.READ);
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
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
	
	public CanjeDTO toDTO() {
		CanjeDTO canjeDto = new CanjeDTO();
		canjeDto.setId(this.id);
		canjeDto.setFecha(this.fecha);
		canjeDto.setProducto(this.producto.toDTO());
		
		return canjeDto;
	}

}
