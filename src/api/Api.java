package api;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ta.TransparentPersistenceSupport;

import dao.CanjeDaoImpl;
import dao.CatalogoDaoImpl;
import dao.CiudadanoDaoImpl;
import dao.ProductoDaoImpl;
import dto.CanjeDTO;
import dto.CatalogoDTO;
import dto.CiudadanoDTO;
import dto.ProductoDTO;
import modelo.Canje;
import modelo.Catalogo;
import modelo.Ciudadano;
import modelo.Producto;
import modelo.Reclamo;

public class Api {

	private String nombreDB = "reclamos";
	private static ObjectContainer db = null;

	private CiudadanoDaoImpl ciudadanoDAO = new CiudadanoDaoImpl();
	private ProductoDaoImpl productoDao = new ProductoDaoImpl();
	private CatalogoDaoImpl catalogoDao = new CatalogoDaoImpl();
	private CanjeDaoImpl canjeDao = new CanjeDaoImpl();

	public void conectarDB() {
		if (db == null) {
			EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
			config.common().add(new TransparentPersistenceSupport());
			config.common().objectClass(Catalogo.class).cascadeOnUpdate(true);
			config.common().objectClass(Ciudadano.class).cascadeOnUpdate(true);
			config.common().objectClass(Reclamo.class).cascadeOnUpdate(true);
			db = Db4oEmbedded.openFile(config, nombreDB);
		} else {
			System.out.println("Desconecte primero la Base de Datos");
		}
	}

	public void desconectarDB() {
		if (db == null) {
			System.out.println("Primero se debe cargar la base de datos");
		} else {
			db.close();
			db = null;
		}
	}

	// METODOS CIUDADANO
	public CiudadanoDTO crearCiudadano(CiudadanoDTO ciudadanoDto) {
		Ciudadano ciudadano = new Ciudadano(ciudadanoDto);
		ciudadanoDAO.salvar(db, ciudadano);

		return ciudadano.toDTO();
	}

	public void eliminarCiudadano(CiudadanoDTO ciudadanoDto) {
		Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, ciudadanoDto.getDni());
		ciudadanoDAO.borrar(db, ciudadano);
	}

	public CiudadanoDTO buscarCiudadano(CiudadanoDTO ciudadanoDto) {

		Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, ciudadanoDto.getDni());

		return ciudadano.toDTO();
	}

	public CiudadanoDTO modificarCiudadano(CiudadanoDTO ciudadanoDto) {
		Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, ciudadanoDto.getDni());
		ciudadano.setNombre(ciudadanoDto.getNombre());
		ciudadano.setApellido(ciudadanoDto.getApellido());
		ciudadano.setEmail(ciudadanoDto.getEmail());
		ciudadano.setPuntos(ciudadanoDto.getPuntos());

		db.commit();
		return ciudadano.toDTO();
	}

	public void listarCiudadanos() {
		ObjectSet<Ciudadano> ciudadanos = ciudadanoDAO.buscarTodos(db);
		for (Ciudadano ciudadano : ciudadanos) {
			System.out.println("Nombre: " + ciudadano.getNombre() + " " + ciudadano.getApellido());
			System.out.println("DNI: " + ciudadano.getDni());
			List<Canje> lcanje = ciudadano.getCanjes();
			int i=1;
			for (Canje canje : lcanje) {
				System.out.println(i+"- "+canje.toString());
				i++;
			}
		}
	}

	public void listarCiudadano(CiudadanoDTO ciudadanoDto) {

	}
	// FIN METODOS CIUDADANO

	// METODOS PRODUCTOS
	public ProductoDTO crearProducto(ProductoDTO productoDto) {
		Producto producto = new Producto(productoDto);
		productoDao.salvar(db, producto);

		return producto.toDTO();
	}

	public ProductoDTO buscarProducto(ProductoDTO productoDto) {
		Producto producto = productoDao.buscarPorId(db, productoDto.getId());
		return producto.toDTO();
	}

	public ProductoDTO modificarProducto(ProductoDTO productoDto) {
		Producto producto = productoDao.buscarPorId(db, productoDto.getId());
		producto.setNombre(productoDto.getNombre());
		producto.setPuntosrequeridos(productoDto.getPuntosrequeridos());

		return producto.toDTO();
	}

	public void eliminarProducto(ProductoDTO productoDto) {
		Producto producto = productoDao.buscarPorId(db, productoDto.getId());
		productoDao.borrar(db, producto);
	}

	public void listarProductos() {
		ObjectSet<Producto> productos = productoDao.buscarTodos(db);
		for (Producto producto : productos) {
			System.out.println("Nombre: " + producto.getNombre());
			System.out.println("Puntos requeridos: " + producto.getPuntosrequeridos());
		}
	}

	// METODOS CATALOGOS
	public CatalogoDTO crearCatalogo(CatalogoDTO catalogoDto) {
		Catalogo catalogo = new Catalogo(catalogoDto);
		catalogoDao.salvar(db, catalogo);

		return catalogo.toDTO();
	}

	public CatalogoDTO agregarProductoaCatalogo(CatalogoDTO catalogoDto, ProductoDTO productoDto) {
		Catalogo catalogo = catalogoDao.buscarPorId(db, catalogoDto.getId());
		catalogo.agregarProducto(productoDto);
		catalogoDao.salvar(db, catalogo);

		return catalogo.toDTO();
	}

	public CatalogoDTO buscarCatalogo(CatalogoDTO catalogoDto) {

		Catalogo catalogo = catalogoDao.buscarPorId(db, catalogoDto.getId());

		return catalogo.toDTO();
	}

	public CatalogoDTO modificarCatalogo(CatalogoDTO catalogoDto) {
		Catalogo catalogo = catalogoDao.buscarPorId(db, catalogoDto.getId());

		catalogo.setNombre(catalogoDto.getNombre());
		catalogo.setProductos(catalogoDto.getProductos());

		db.commit();
		return catalogo.toDTO();
	}

	public void eliminarCatalogo(CatalogoDTO catalogoDto) {
		Catalogo catalogo = catalogoDao.buscarPorId(db, catalogoDto.getId());
		catalogoDao.borrar(db, catalogo);
	}

	public void listarCatalogos() {
		ObjectSet<Catalogo> catalogos = catalogoDao.buscarTodos(db);
		for (Catalogo catalogo : catalogos) {
			System.out.println("Catalogo: " + catalogo.toString());

		}
	}

	// METODOS CANJES
	public CanjeDTO crearCanje(CanjeDTO canjeDto) {
		Canje canje = new Canje(canjeDto);
		canjeDao.salvar(db, canje);

		return canje.toDTO();
	}

	public CanjeDTO buscarCanje(CanjeDTO canjeDto) {

		Canje canje = canjeDao.buscarPorId(db, canjeDto.getId());

		return canje.toDTO();
	}

	public CanjeDTO modificarCanje(CanjeDTO canjeDto) {
		Canje canje = canjeDao.buscarPorId(db, canjeDto.getId());

		canje.setFecha(canjeDto.getFecha());
		canje.setProducto(canjeDto.getProducto());

		db.commit();
		return canje.toDTO();
	}

	public void eliminarCanje(CanjeDTO canjeDto) {
		Canje canje = canjeDao.buscarPorId(db, canjeDto.getId());
		canjeDao.borrar(db, canje);
	}

	public void realizarCanje(CiudadanoDTO ciudadanoDto, ProductoDTO productoDto) {
		Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, ciudadanoDto.getDni());
		Producto producto = new Producto(productoDto);
		ciudadano.canjearPuntos(producto);

	}

	public void listarCanjes() {
		ObjectSet<Canje> canjes = canjeDao.buscarTodos(db);
		for (Canje canje : canjes) {
			System.out.println("Canje: " + canje.toString());

		}
	}
}
