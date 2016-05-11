package modelo;

import java.util.ArrayList;
import java.util.UUID;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

import dto.CiudadanoDTO;
import dto.ProductoDTO;

public class Producto implements Activatable {

	private String id = null;
	private String nombre;
	private int puntosrequeridos;
	private transient Activator _activator;

	public Producto(String nombre, int puntosrequeridos) {
		super();
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.puntosrequeridos = puntosrequeridos;
	}

	public Producto(String id, String nombre, int puntosrequeridos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.puntosrequeridos = puntosrequeridos;
	}

	public Producto(ProductoDTO productoDto) {
		this.id = UUID.randomUUID().toString();
		this.nombre = productoDto.getNombre();
		this.puntosrequeridos = productoDto.getPuntosrequeridos();
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

	public String getId() {
		activate(ActivationPurpose.READ);
		return id;
	}

	public void setId(String id) {
		activate(ActivationPurpose.WRITE);
		this.id = id;
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
		return " Producto [id=" + id + ",nombre=" + nombre + ", puntosrequeridos=" + puntosrequeridos + "]";
	}

	public ProductoDTO toDTO() {
		ProductoDTO productoDto = new ProductoDTO();

		productoDto.setId(this.getId());
		productoDto.setNombre(this.getNombre());
		productoDto.setPuntosrequeridos(this.getPuntosrequeridos());

		return productoDto;
	}
}
