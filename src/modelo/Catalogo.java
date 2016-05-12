package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.db4o.activation.ActivationPurpose;
import com.db4o.activation.Activator;
import com.db4o.ta.Activatable;

import dto.CanjeDTO;
import dto.CatalogoDTO;
import dto.ProductoDTO;

public class Catalogo implements Activatable {

	private String id = null;
	private String nombre;
	private List<Producto> productos;
	private transient Activator _activator;

	public Catalogo(String nombre) {
		super();
		this.id = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.productos = new ArrayList<Producto>();
	}

	public Catalogo(String id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.productos = null;
	}

	public Catalogo(CatalogoDTO catalogoDto) {
		this.id = UUID.randomUUID().toString();
		this.nombre = catalogoDto.getNombre();
		this.productos = new ArrayList<Producto>();
	}

	public String getId() {
		activate(ActivationPurpose.READ);
		return id;
	}

	public void setId(String id) {
		activate(ActivationPurpose.WRITE);
		this.id = id;
	}

	public String getNombre() {
		activate(ActivationPurpose.READ);
		return nombre;
	}

	public void setNombre(String nombre) {
		activate(ActivationPurpose.WRITE);
		this.nombre = nombre;
	}

	public List<Producto> getProductos() {
		activate(ActivationPurpose.READ);
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		activate(ActivationPurpose.WRITE);
		this.productos = productos;
	}

	public void agregarProducto(Producto prod) {
		List<Producto> listaproducto = this.getProductos();
		listaproducto.add(prod);
		this.setProductos(listaproducto);
	}
	
	public void agregarProducto(ProductoDTO prodDto) {
		List<Producto> listaproducto = this.getProductos();
		Producto prod = new Producto(prodDto);
		listaproducto.add(prod);
		this.setProductos(listaproducto);
	}

	public void eliminarProducto(Producto prod) {
		List<Producto> listaproducto = this.getProductos();
		listaproducto.remove(prod);
		this.setProductos(listaproducto);
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
		return "Catalogo [nombre=" + nombre + ", productos=" + productos + "]";
	}

	public CatalogoDTO toDTO() {
		CatalogoDTO catalogoDto = new CatalogoDTO();
		catalogoDto.setId(this.id);
		catalogoDto.setNombre(this.nombre);
		List<ProductoDTO> lproductos = new ArrayList<ProductoDTO>();
		for (Producto producto : productos) {
			lproductos.add(producto.toDTO());
		}

		return catalogoDto;
	}
}
