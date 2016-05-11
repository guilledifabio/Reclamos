package modelo;

import java.util.UUID;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

import dto.CategoriaDTO;

public class Categoria implements Activatable {
	
	private String id = null;
	private String nombre;
	private String descripcion;
	private int puntos;
	private transient Activator _activator;

	public Categoria(String nombre, String descripcion, int puntos) {
		super();
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.puntos = puntos;
	}

	public Categoria(String id, String nombre, String descripcion, int puntos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.puntos = puntos;
	}

	public String getNombre() {
		activate(ActivationPurpose.READ);
		return nombre;
	}

	public void setNombre(String nombre) {
		activate(ActivationPurpose.WRITE);
		this.nombre = nombre;
	}

	public String getDescripcion() {
		activate(ActivationPurpose.READ);
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		activate(ActivationPurpose.WRITE);
		this.descripcion = descripcion;
	}

	public int getPuntos() {
		activate(ActivationPurpose.READ);
		return puntos;
	}

	public void setPuntos(int puntos) {
		activate(ActivationPurpose.WRITE);
		this.puntos = puntos;
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
		return this.getNombre();
	}
	
	public CategoriaDTO toDTO() {
		CategoriaDTO categoriaDto = new CategoriaDTO();
		categoriaDto.setId(this.id);
		categoriaDto.setNombre(this.nombre);
		categoriaDto.setDescripcion(this.descripcion);
		categoriaDto.setPuntos(this.puntos);
		
		return categoriaDto;
	}
}
