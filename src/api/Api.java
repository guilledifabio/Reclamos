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
	private ObjectContainer session = null;

	private CiudadanoDaoImpl ciudadanoDAO = new CiudadanoDaoImpl();
	private CategoriaDaoImpl categoriaDAO = new CategoriaDaoImpl();
	private ReclamoDaoImpl reclamoDAO = new ReclamoDaoImpl();
	private EventoDaoImpl eventoDAO = new EventoDaoImpl();
	private CatalogoDaoImpl catalogoDAO = new CatalogoDaoImpl();
	private ProductoDaoImpl productoDAO = new ProductoDaoImpl();
	private CanjeDaoImpl canjeDAO = new CanjeDaoImpl();

	public Api() {
		super();
		EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
		config.common().add(new TransparentPersistenceSupport());
		config.common().objectClass(Catalogo.class).cascadeOnUpdate(true);
		config.common().objectClass(Ciudadano.class).cascadeOnUpdate(true);
		config.common().objectClass(Reclamo.class).cascadeOnUpdate(true);
		config.common().objectClass(Categoria.class).cascadeOnUpdate(true);
		try {
			db = Db4oEmbedded.openFile(config, nombreDB);
		} catch (Exception e) {
			System.out.println("Error al abrir el archivo de base de datos");
		}
	}

	private void conectarDB() {
		try {
			session = db.ext().openSession();
		} catch (Exception e) {
			System.out.println("Error al conectar a la base de datos");
		}

	}

	private void desconectarDB() {
		try {
			session.close();
		} catch (Exception e) {
			System.out.println("Error al desconectar base de datos");
		}

	}

	// METODOS CIUDADANO
	public CiudadanoDTO crearCiudadano(CiudadanoDTO ciudadanoDto) {
		conectarDB();
		try {
			Ciudadano ciudadano = new Ciudadano(ciudadanoDto);
			ciudadanoDAO.salvar(db, ciudadano);
			ciudadanoDto = ciudadano.toDTO();
		} finally {
			desconectarDB();
		}

		return ciudadanoDto;
	}

	public void eliminarCiudadano(CiudadanoDTO ciudadanoDto) {
		conectarDB();
		try {
			Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, ciudadanoDto.getDni());
			ciudadanoDAO.borrar(db, ciudadano);
		} finally {
			desconectarDB();
		}
	}

	public CiudadanoDTO buscarCiudadano(CiudadanoDTO ciudadanoDto) {
		conectarDB();
		try {
			Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, ciudadanoDto.getDni());
			ciudadanoDto = ciudadano.toDTO();
		} finally {
			desconectarDB();
		}
		
		return ciudadanoDto;
	}

	public CiudadanoDTO modificarCiudadano(CiudadanoDTO ciudadanoDto) {
		conectarDB();
		try {
			Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, ciudadanoDto.getDni());
			ciudadano.setNombre(ciudadanoDto.getNombre());
			ciudadano.setApellido(ciudadanoDto.getApellido());
			ciudadano.setEmail(ciudadanoDto.getEmail());
			ciudadano.setPuntos(ciudadanoDto.getPuntos());
			db.commit();
			ciudadanoDto = ciudadano.toDTO();
		} finally {
			desconectarDB();
		}
		
		return ciudadanoDto;
	}

	public void listarCiudadanos() {
		conectarDB();
		try {
			ObjectSet<Ciudadano>  ciudadanos = ciudadanoDAO.buscarTodos(db);
			for (Ciudadano ciudadano : ciudadanos) {
				System.out.println("Nombre: " + ciudadano.getNombre() + " " + ciudadano.getApellido());
				System.out.println("DNI: " + ciudadano.getDni());
				System.out.println("Canjes: ");
				List<Canje> lcanje = ciudadano.getCanjes();
				for (Canje canje : lcanje) {
					System.out.println(canje.toString());
				}
			}
		} finally {
			desconectarDB();
		}
	}

	public void listarCiudadano(CiudadanoDTO ciudadanoDto) {
		conectarDB();
		try {
			Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, ciudadanoDto.getDni());
			List<Canje> lcanjes = ciudadano.getCanjes();
			System.out.println("Nombre: " + ciudadano.getNombre() + " " + ciudadano.getApellido());
			System.out.println("DNI: " + ciudadano.getDni());
			System.out.println("Canjes: ");
			for (Canje canje : lcanjes) {
				System.out.println(canje.toString());
			}
		} finally {
			desconectarDB();
		}
	}
	// FIN METODOS CIUDADANO

	// METODOS CATEGORIA
	public CategoriaDTO crearCategoria(CategoriaDTO categoriaDto) {
		conectarDB();
		try {
			Categoria categoria = new Categoria(categoriaDto);
			categoriaDAO.salvar(db, categoria);
			categoriaDto = categoria.toDTO();
		} finally {
			desconectarDB();
		}
		
		return categoriaDto;
	}

	public void eliminarCategoria(CategoriaDTO categoriaDto) {
		conectarDB();
		try {
			Categoria categoria = categoriaDAO.buscarPorCategoria(db, categoriaDto.getId());
			categoriaDAO.borrar(db, categoria);
		} finally {
			desconectarDB();
		}
	}

	public CategoriaDTO modificarCategoria(CategoriaDTO categoriaDto) {
		conectarDB();
		try {
			Categoria categoria = categoriaDAO.buscarPorCategoria(db, categoriaDto.getId());
			categoria.setNombre(categoriaDto.getNombre());
			categoria.setDescripcion(categoriaDto.getDescripcion());
			categoria.setPuntos(categoriaDto.getPuntos());
			db.commit();
			categoriaDto = categoria.toDTO();
		} finally {
			desconectarDB();
		}
		
		return categoriaDto;
	}

	public List<CategoriaDTO> listarCategorias() {
		conectarDB();
		List<CategoriaDTO> lcategoria = new ArrayList<CategoriaDTO>();
		try {
			ObjectSet<Categoria> categorias = categoriaDAO.buscarTodos(db);
			for (Categoria categoria : categorias) {
				lcategoria.add(categoria.toDTO());
				// System.out.println("Categoria Nombre: " + categoria.getNombre() +
				// " Descripcion: "
				// + categoria.getDescripcion() + " Puntos: " +
				// categoria.getPuntos());
			}
		} finally {
			desconectarDB();
		}
		
		return lcategoria;
	}

	// FIN METODOS CATEGORIA

	// METODOS RECLAMOS
	public void crearReclamo(CiudadanoDTO ciudadanoDto, ReclamoDTO reclamoDto) {
		conectarDB();
		try {
			Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, ciudadanoDto.getDni());
			Categoria categoria = categoriaDAO.buscarPorCategoria(db, reclamoDto.getCategoria().getId());
			Reclamo reclamo = new Reclamo(reclamoDto, categoria);
			ciudadano.realizarReclamo(reclamo);
			db.commit();
		} finally {
			desconectarDB();
		}
	}

	public ReclamoDTO modificarReclamo(ReclamoDTO reclamoDto) {
		conectarDB();
		try {
			Reclamo reclamo = reclamoDAO.buscarPorId(db, reclamoDto.getId());
			reclamo.setDescripcion(reclamoDto.getDescripcion());
			reclamo.setDireccion(reclamoDto.getDireccion());
			db.commit();
			reclamoDto = reclamo.toDTO();
		} finally {
			desconectarDB();
		}
		
		return reclamoDto;
	}

	public ReclamoDTO modificarCategoria(ReclamoDTO reclamoDto, CategoriaDTO categoriaDto) {
		conectarDB();
		try {
			Reclamo reclamo = reclamoDAO.buscarPorId(db, reclamoDto.getId());
			Categoria categoria = categoriaDAO.buscarPorCategoria(db, categoriaDto.getId());
			reclamo.setCategoria(categoria);
			db.commit();
			reclamoDto = reclamo.toDTO();
		} finally {
			desconectarDB();
		}
		
		return reclamoDto;
	}

	public List<ReclamoDTO> listarReclamoCiudadano(CiudadanoDTO ciudadanoDto) {
		conectarDB();
		List<ReclamoDTO> lreclamosDto = new ArrayList<ReclamoDTO>();
		try {
			Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, ciudadanoDto.getDni());
			List<Reclamo> lreclamos = ciudadano.getReclamos();
			for (Reclamo reclamo : lreclamos) {
				// System.out.println("Reclamos del ciudadano; " +
				// ciudadano.getNombre() + " " + ciudadano.getApellido());
				// System.out.println(reclamo.toString());
				lreclamosDto.add(reclamo.toDTO());
			}
		} finally {
			desconectarDB();
		}
		
		return lreclamosDto;
	}
	// FIN METODOS RECLAMOS

	// METODOS EVENTO
	public ReclamoDTO agregarEventoReclamo(ReclamoDTO reclamoDto, EventoDTO eventoDto) {
		conectarDB();
		try {
			Evento evento = new Evento(eventoDto);
			Reclamo reclamo = reclamoDAO.buscarPorId(db, reclamoDto.getId());
			List<Evento> leventos = reclamo.getEventos();
			leventos.add(evento);
			reclamo.setEventos(leventos);
			db.commit();
			reclamoDto = reclamo.toDTO();
		} finally {
			desconectarDB();
		}
		
		return reclamoDto;
	}

	public void modificarEventoReclamo(EventoDTO eventoDto) {
		conectarDB();
		try {
			Evento evento = eventoDAO.buscarPorId(db, eventoDto.getId());
			evento.setDescripcion(eventoDto.getDescripcion());
			db.commit();
		} finally {
			desconectarDB();
		}
	}

	public void eliminarEventoDeReclamo(ReclamoDTO reclamoDto, EventoDTO eventoDto) {
		conectarDB();
		try {
			Reclamo reclamo = reclamoDAO.buscarPorId(db, reclamoDto.getId());
			Evento evento = eventoDAO.buscarPorId(db, eventoDto.getId());
			List<Evento> leventos = reclamo.getEventos();
			leventos.remove(evento);
			eventoDAO.borrar(db, evento);
			db.commit();
		} finally {
			desconectarDB();
		}
	}

	public List<EventoDTO> listarEventosReclamo(ReclamoDTO reclamoDto) {
		conectarDB();
		List<EventoDTO> leventosDto = new ArrayList<EventoDTO>();
		try {
			Reclamo reclamo = reclamoDAO.buscarPorId(db, reclamoDto.getId());
			List<Evento> leventos = reclamo.getEventos();
			for (Evento evento : leventos) {
				leventosDto.add(evento.toDTO());
			}
			System.out.println(reclamo.toString());
		} finally {
			desconectarDB();
		}

		return leventosDto;
	}
	// FIN METODOS EVENTO

	// METODOS PRODUCTOS
	public ProductoDTO crearProducto(ProductoDTO productoDto) {
		conectarDB();
		try {
			Producto producto = new Producto(productoDto);
			productoDAO.salvar(db, producto);
			productoDto = producto.toDTO();
		} finally {
			desconectarDB();
		}
		

		return productoDto;
	}

	public ProductoDTO buscarProducto(ProductoDTO productoDto) {
		conectarDB();
		try {
			Producto producto = productoDAO.buscarPorId(db, productoDto.getId());
			productoDto = producto.toDTO();
		} finally {
			desconectarDB();
		}
	
		return productoDto;
	}

	public ProductoDTO modificarProducto(ProductoDTO productoDto) {
		conectarDB();
		try {
			Producto producto = productoDAO.buscarPorId(db, productoDto.getId());
			producto.setNombre(productoDto.getNombre());
			producto.setPuntosrequeridos(productoDto.getPuntosrequeridos());
			productoDto = producto.toDTO();
		} finally {
			desconectarDB();
		}

		return productoDto;
	}

	public void eliminarProducto(ProductoDTO productoDto) {
		conectarDB();
		try {
			Producto producto = productoDAO.buscarPorId(db, productoDto.getId());
			productoDAO.borrar(db, producto);
		} finally {
			desconectarDB();
		}
	}

	public void listarProductos() {
		conectarDB();
		try {
			ObjectSet<Producto> productos = productoDAO.buscarTodos(db);
			for (Producto producto : productos) {
				System.out.println("Nombre: " + producto.getNombre());
				System.out.println("Puntos requeridos: " + producto.getPuntosrequeridos());
			}
		} finally {
			desconectarDB();
		}
	}

	// METODOS CATALOGOS
	public CatalogoDTO crearCatalogo(CatalogoDTO catalogoDto) {
		conectarDB();
		try {
			Catalogo catalogo = new Catalogo(catalogoDto);
			catalogoDAO.salvar(db, catalogo);
			catalogoDto = catalogo.toDTO();
		} finally {
			desconectarDB();
		}
		
		return catalogoDto;
	}

	public CatalogoDTO agregarProductoaCatalogo(CatalogoDTO catalogoDto, ProductoDTO productoDto) {
		conectarDB();
		try {
			Catalogo catalogo = catalogoDAO.buscarPorId(db, catalogoDto.getId());
			catalogo.agregarProducto(productoDto);
			catalogoDAO.salvar(db, catalogo);
			catalogoDto = catalogo.toDTO();
		} finally {
			desconectarDB();
		}
		

		return catalogoDto;
	}

	public CatalogoDTO buscarCatalogo(CatalogoDTO catalogoDto) {
		conectarDB();
		try {
			Catalogo catalogo = catalogoDAO.buscarPorId(db, catalogoDto.getId());
			catalogoDto = catalogo.toDTO();
		} finally {
			desconectarDB();
		}

		return catalogoDto;
	}

	public CatalogoDTO modificarCatalogo(CatalogoDTO catalogoDto) {
		conectarDB();
		try {
			Catalogo catalogo = catalogoDAO.buscarPorId(db, catalogoDto.getId());
			catalogo.setNombre(catalogoDto.getNombre());
			List<ProductoDTO> lproductos = catalogoDto.getProductos();
			if (lproductos != null) {
				for (ProductoDTO productosDTO : lproductos) {
					productosDTO = modificarProducto(productosDTO);
				}
			}

			// catalogo.setNombre(catalogoDto.getNombre());
			// catalogo.setProductos(catalogoDto.getProductos());

			db.commit();
			catalogoDto = catalogo.toDTO();
		} finally {
			desconectarDB();
		}

		return catalogoDto;
	}

	public void eliminarCatalogo(CatalogoDTO catalogoDto) {
		conectarDB();
		try {
			Catalogo catalogo = catalogoDAO.buscarPorId(db, catalogoDto.getId());
			catalogoDAO.borrar(db, catalogo);
		} finally {
			desconectarDB();
		}
	}

	public void listarCatalogos() {
		conectarDB();
		try {
			ObjectSet<Catalogo> catalogos = catalogoDAO.buscarTodos(db);
			for (Catalogo catalogo : catalogos) {
				System.out.println("Catalogo: " + catalogo.toString());
			}
		} finally {
			desconectarDB();
		}
	}

	// METODOS CANJES
	public CanjeDTO crearCanje(CanjeDTO canjeDto) {
		conectarDB();
		try {
			Canje canje = new Canje(canjeDto);
			canjeDAO.salvar(db, canje);
			canjeDto = canje.toDTO();
		} finally {
			desconectarDB();
		}
	
		return canjeDto;
	}

	public CanjeDTO buscarCanje(CanjeDTO canjeDto) {
		conectarDB();
		try {
			Canje canje = canjeDAO.buscarPorId(db, canjeDto.getId());
			canjeDto = canje.toDTO();
		} finally {
			desconectarDB();
		}
		
		return canjeDto;
	}

	public CanjeDTO modificarCanje(CanjeDTO canjeDto) {
		conectarDB();
		try {
			Canje canje = canjeDAO.buscarPorId(db, canjeDto.getId());
			Producto producto = productoDAO.buscarPorId(db, canjeDto.getProducto().getId());
			canje.setFecha(canjeDto.getFecha());
			canje.setProducto(producto);
			db.commit();
			canjeDto = canje.toDTO();
		} finally {
			desconectarDB();
		}
		
		return canjeDto;
	}

	public void eliminarCanje(CanjeDTO canjeDto) {
		conectarDB();
		try {
			Canje canje = canjeDAO.buscarPorId(db, canjeDto.getId());
			canjeDAO.borrar(db, canje);
		} finally {
			desconectarDB();
		}
	}

	public void realizarCanje(CiudadanoDTO ciudadanoDto, ProductoDTO productoDto) {
		conectarDB();
		try {
			Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, ciudadanoDto.getDni());
			Producto producto = new Producto(productoDto);
			ciudadano.canjearPuntos(producto);
		} finally {
			desconectarDB();
		}
	}

	public void listarCanjes() {
		conectarDB();
		try {
			ObjectSet<Canje> canjes = canjeDAO.buscarTodos(db);
			for (Canje canje : canjes) {
				System.out.println("Canje: " + canje.toString());
			}
		} finally {
			desconectarDB();
		}
	}
}
