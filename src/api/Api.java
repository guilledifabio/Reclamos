package api;

import java.util.ArrayList;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ta.TransparentPersistenceSupport;

import dao.CanjeDaoImpl;
import dao.CatalogoDaoImpl;
import dao.CategoriaDaoImpl;
import dao.CiudadanoDaoImpl;
import dao.EventoDaoImpl;
import dao.ProductoDaoImpl;
import dao.ReclamoDaoImpl;
import dto.CanjeDTO;
import dto.CatalogoDTO;
import dto.CategoriaDTO;
import dto.CiudadanoDTO;
import dto.EventoDTO;
import dto.ProductoDTO;
import dto.ReclamoDTO;
import modelo.Canje;
import modelo.Catalogo;
import modelo.Categoria;
import modelo.Ciudadano;
import modelo.Evento;
import modelo.Producto;
import modelo.Reclamo;

public class Api {

	private String nombreDB = "reclamos";
	private static ObjectContainer db = null;

	private CiudadanoDaoImpl ciudadanoDAO = new CiudadanoDaoImpl();
	private CategoriaDaoImpl categoriaDAO = new CategoriaDaoImpl();
	private ReclamoDaoImpl reclamoDAO = new ReclamoDaoImpl();
	private EventoDaoImpl eventoDAO = new EventoDaoImpl();
	private CatalogoDaoImpl catalogoDAO = new CatalogoDaoImpl();
	private ProductoDaoImpl productoDAO = new ProductoDaoImpl();
	private CanjeDaoImpl canjeDAO = new CanjeDaoImpl();

	public void conectarDB() {
		if (db == null) {
			EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
			config.common().add(new TransparentPersistenceSupport());
			config.common().objectClass(Catalogo.class).cascadeOnUpdate(true);
			config.common().objectClass(Ciudadano.class).cascadeOnUpdate(true);
			config.common().objectClass(Reclamo.class).cascadeOnUpdate(true);
			config.common().objectClass(Categoria.class).cascadeOnUpdate(true);
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
			System.out.println("Canjes: ");
			List<Canje> lcanje = ciudadano.getCanjes();
			for (Canje canje : lcanje) {
				System.out.println(canje.toString());
			}
		}
	}

	public void listarCiudadano(CiudadanoDTO ciudadanoDto) {
		Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, ciudadanoDto.getDni());
		List<Canje> lcanjes = ciudadano.getCanjes();
		System.out.println("Nombre: " + ciudadano.getNombre() + " " + ciudadano.getApellido());
		System.out.println("DNI: " + ciudadano.getDni());
		System.out.println("Canjes: ");
		for (Canje canje : lcanjes) {
			System.out.println(canje.toString());
		}
	}
	// FIN METODOS CIUDADANO

	// METODOS CATEGORIA
	public CategoriaDTO crearCategoria(CategoriaDTO categoriaDto) {
		Categoria categoria = new Categoria(categoriaDto);
		categoriaDAO.salvar(db, categoria);
		return categoria.toDTO();
	}

	public void eliminarCategoria(CategoriaDTO categoriaDto) {
		Categoria categoria = categoriaDAO.buscarPorCategoria(db, categoriaDto.getId());
		categoriaDAO.borrar(db, categoria);
	}

	public CategoriaDTO modificarCategoria(CategoriaDTO categoriaDto) {
		Categoria categoria = categoriaDAO.buscarPorCategoria(db, categoriaDto.getId());
		categoria.setNombre(categoriaDto.getNombre());
		categoria.setDescripcion(categoriaDto.getDescripcion());
		categoria.setPuntos(categoriaDto.getPuntos());

		db.commit();
		return categoria.toDTO();
	}

	public List<CategoriaDTO> listarCategorias() {
		ObjectSet<Categoria> categorias = categoriaDAO.buscarTodos(db);
		List<CategoriaDTO> lcategoria = new ArrayList<CategoriaDTO>();

		for (Categoria categoria : categorias) {
			lcategoria.add(categoria.toDTO());
			// System.out.println("Categoria Nombre: " + categoria.getNombre() +
			// " Descripcion: "
			// + categoria.getDescripcion() + " Puntos: " +
			// categoria.getPuntos());
		}

		return lcategoria;
	}

	// FIN METODOS CATEGORIA

	// METODOS RECLAMOS
	public void crearReclamo(CiudadanoDTO ciudadanoDto, ReclamoDTO reclamoDto) {
		Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, ciudadanoDto.getDni());
		Categoria categoria = categoriaDAO.buscarPorCategoria(db, reclamoDto.getCategoria().getId());
		Reclamo reclamo = new Reclamo(reclamoDto, categoria);
		ciudadano.realizarReclamo(reclamo);

		db.commit();
	}

	public ReclamoDTO modificarReclamo(ReclamoDTO reclamoDto) {
		Reclamo reclamo = reclamoDAO.buscarPorId(db, reclamoDto.getId());
		reclamo.setDescripcion(reclamoDto.getDescripcion());
		reclamo.setDireccion(reclamoDto.getDireccion());

		db.commit();
		return reclamo.toDTO();
	}

	public ReclamoDTO modificarCategoria(ReclamoDTO reclamoDto, CategoriaDTO categoriaDto) {
		Reclamo reclamo = reclamoDAO.buscarPorId(db, reclamoDto.getId());
		Categoria categoria = categoriaDAO.buscarPorCategoria(db, categoriaDto.getId());
		reclamo.setCategoria(categoria);

		db.commit();
		return reclamo.toDTO();
	}

	public List<ReclamoDTO> listarReclamoCiudadano(CiudadanoDTO ciudadanoDto) {
		Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, ciudadanoDto.getDni());
		List<Reclamo> lreclamos = ciudadano.getReclamos();
		List<ReclamoDTO> lreclamosDto = new ArrayList<ReclamoDTO>();
		for (Reclamo reclamo : lreclamos) {
			// System.out.println("Reclamos del ciudadano; " +
			// ciudadano.getNombre() + " " + ciudadano.getApellido());
			// System.out.println(reclamo.toString());
			lreclamosDto.add(reclamo.toDTO());
		}

		return lreclamosDto;
	}
	// FIN METODOS RECLAMOS

	// METODOS EVENTO
	public ReclamoDTO agregarEventoReclamo(ReclamoDTO reclamoDto, EventoDTO eventoDto) {
		Evento evento = new Evento(eventoDto);
		Reclamo reclamo = reclamoDAO.buscarPorId(db, reclamoDto.getId());
		List<Evento> leventos = reclamo.getEventos();
		leventos.add(evento);
		reclamo.setEventos(leventos);

		db.commit();
		return reclamo.toDTO();
	}

	public void modificarEventoReclamo(EventoDTO eventoDto) {
		Evento evento = eventoDAO.buscarPorId(db, eventoDto.getId());
		evento.setDescripcion(eventoDto.getDescripcion());

		db.commit();
	}

	public void eliminarEventoDeReclamo(ReclamoDTO reclamoDto, EventoDTO eventoDto) {
		Reclamo reclamo = reclamoDAO.buscarPorId(db, reclamoDto.getId());
		Evento evento = eventoDAO.buscarPorId(db, eventoDto.getId());
		List<Evento> leventos = reclamo.getEventos();
		leventos.remove(evento);

		eventoDAO.borrar(db, evento);

		db.commit();
	}

	public List<EventoDTO> listarEventosReclamo(ReclamoDTO reclamoDto) {
		Reclamo reclamo = reclamoDAO.buscarPorId(db, reclamoDto.getId());
		List<Evento> leventos = reclamo.getEventos();
		List<EventoDTO> leventosDto = new ArrayList<EventoDTO>();

		for (Evento evento : leventos) {
			leventosDto.add(evento.toDTO());
		}
		System.out.println(reclamo.toString());

		return leventosDto;
	}
	// FIN METODOS EVENTO

	// METODOS PRODUCTOS
	public ProductoDTO crearProducto(ProductoDTO productoDto) {
		Producto producto = new Producto(productoDto);
		productoDAO.salvar(db, producto);

		return producto.toDTO();
	}

	public ProductoDTO buscarProducto(ProductoDTO productoDto) {
		Producto producto = productoDAO.buscarPorId(db, productoDto.getId());
		return producto.toDTO();
	}

	public ProductoDTO modificarProducto(ProductoDTO productoDto) {
		Producto producto = productoDAO.buscarPorId(db, productoDto.getId());
		producto.setNombre(productoDto.getNombre());
		producto.setPuntosrequeridos(productoDto.getPuntosrequeridos());

		return producto.toDTO();
	}

	public void eliminarProducto(ProductoDTO productoDto) {
		Producto producto = productoDAO.buscarPorId(db, productoDto.getId());
		productoDAO.borrar(db, producto);
	}

	public void listarProductos() {
		ObjectSet<Producto> productos = productoDAO.buscarTodos(db);
		for (Producto producto : productos) {
			System.out.println("Nombre: " + producto.getNombre());
			System.out.println("Puntos requeridos: " + producto.getPuntosrequeridos());
		}
	}

	// METODOS CATALOGOS
	public CatalogoDTO crearCatalogo(CatalogoDTO catalogoDto) {
		Catalogo catalogo = new Catalogo(catalogoDto);
		catalogoDAO.salvar(db, catalogo);

		return catalogo.toDTO();
	}

	public CatalogoDTO agregarProductoaCatalogo(CatalogoDTO catalogoDto, ProductoDTO productoDto) {
		Catalogo catalogo = catalogoDAO.buscarPorId(db, catalogoDto.getId());
		catalogo.agregarProducto(productoDto);
		catalogoDAO.salvar(db, catalogo);

		return catalogo.toDTO();
	}

	public CatalogoDTO buscarCatalogo(CatalogoDTO catalogoDto) {

		Catalogo catalogo = catalogoDAO.buscarPorId(db, catalogoDto.getId());

		return catalogo.toDTO();
	}

	public CatalogoDTO modificarCatalogo(CatalogoDTO catalogoDto) {
		Catalogo catalogo = catalogoDAO.buscarPorId(db, catalogoDto.getId());
		catalogo.setNombre(catalogoDto.getNombre());
		List<ProductoDTO> lproductos = catalogoDto.getProductos();
		if (lproductos != null) {
			for (ProductoDTO productosDTO : lproductos) {
				productosDTO = modificarProducto(productosDTO);
			}
		}		

//		catalogo.setNombre(catalogoDto.getNombre());
//		catalogo.setProductos(catalogoDto.getProductos());

		db.commit();
		return catalogo.toDTO();
	}

	public void eliminarCatalogo(CatalogoDTO catalogoDto) {
		Catalogo catalogo = catalogoDAO.buscarPorId(db, catalogoDto.getId());
		catalogoDAO.borrar(db, catalogo);
	}

	public void listarCatalogos() {
		ObjectSet<Catalogo> catalogos = catalogoDAO.buscarTodos(db);
		for (Catalogo catalogo : catalogos) {
			System.out.println("Catalogo: " + catalogo.toString());

		}
	}

	// METODOS CANJES
	public CanjeDTO crearCanje(CanjeDTO canjeDto) {
		Canje canje = new Canje(canjeDto);
		canjeDAO.salvar(db, canje);

		return canje.toDTO();
	}

	public CanjeDTO buscarCanje(CanjeDTO canjeDto) {

		Canje canje = canjeDAO.buscarPorId(db, canjeDto.getId());

		return canje.toDTO();
	}

	public CanjeDTO modificarCanje(CanjeDTO canjeDto) {
		Canje canje = canjeDAO.buscarPorId(db, canjeDto.getId());
		Producto producto = productoDAO.buscarPorId(db, canjeDto.getProducto().getId());
		canje.setFecha(canjeDto.getFecha());
		canje.setProducto(producto);

		db.commit();
		return canje.toDTO();
	}

	public void eliminarCanje(CanjeDTO canjeDto) {
		Canje canje = canjeDAO.buscarPorId(db, canjeDto.getId());
		canjeDAO.borrar(db, canje);
	}

	public void realizarCanje(CiudadanoDTO ciudadanoDto, ProductoDTO productoDto) {
		Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, ciudadanoDto.getDni());
		Producto producto = new Producto(productoDto);
		ciudadano.canjearPuntos(producto);

	}

	public void listarCanjes() {
		ObjectSet<Canje> canjes = canjeDAO.buscarTodos(db);
		for (Canje canje : canjes) {
			System.out.println("Canje: " + canje.toString());

		}
	}
}
