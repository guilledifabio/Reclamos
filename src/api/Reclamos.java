package api;

import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ta.TransparentPersistenceSupport;

import dao.CatalogoDaoImpl;
import dao.CategoriaDaoImpl;
import dao.CiudadanoDaoImpl;
import dao.ProductoDaoImpl;
import dao.ReclamoDaoImpl;
import modelo.*;

public class Reclamos {

	private String nombreDB = "reclamos";
	private static ObjectContainer db = null;
	private Ciudadano activo = null;

	private CiudadanoDaoImpl ciudadanoDAO = new CiudadanoDaoImpl();
	private CatalogoDaoImpl catalogoDAO = new CatalogoDaoImpl();
	private ProductoDaoImpl productoDAO = new ProductoDaoImpl();
	private CategoriaDaoImpl categoriaDAO = new CategoriaDaoImpl();
	private ReclamoDaoImpl reclamosDAO = new ReclamoDaoImpl();

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

	public void signIn(int dni) {

		activo = buscarCiudadano(dni);
		if (activo == null) {
			System.out.println("Ciudadano no existe");
		} else {
			System.out.println("Bienvenido");
		}
	}

	public void crearCiudadano(String nombre, String apellido, int dni, String email) {
		Ciudadano ciudadano = new Ciudadano(nombre, apellido, dni, email);
		ciudadanoDAO.salvar(db, ciudadano);
	}

	public void eliminarCiudadano(int dni) {
		Ciudadano ciudadano = buscarCiudadano(dni);

		if (ciudadano == null) {
			System.out.println("El ciudadano no existe");
		} else {
			ciudadanoDAO.borrar(db, buscarCiudadano(dni));
			System.out.println("Borrado");
		}

		db.commit();
	}

	public void modificarCiudadano(String nombre, String apellido, int dni, String email) {
		Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, dni);
		ciudadano.setApellido(apellido);
		ciudadano.setNombre(nombre);
		ciudadano.setEmail(email);
		db.commit();
	}

	public Ciudadano buscarCiudadano(int dni) {

		Ciudadano ciudadano = ciudadanoDAO.buscarPorDni(db, dni);

		return ciudadano;
	}

	public void crearCatalogo(String nombre) {
		Catalogo catalogo = new Catalogo(nombre);
		catalogoDAO.salvar(db, catalogo);
	}

	public void agregarProductoCatalogo(String nCatalogo, String nProducto, int puntos) {
		Producto producto = new Producto(nProducto, puntos);
		Catalogo catalogo = catalogoDAO.buscarPorNombre(db, nCatalogo);
		catalogo.agregarProducto(producto);
		db.commit();
	}

	public void eliminarProductoCatalogo(String nCatalogo, String nProducto) {
		Catalogo catalogo = catalogoDAO.buscarPorNombre(db, nCatalogo);
		Producto produto = productoDAO.buscar(db, nProducto);
		catalogo.eliminarProducto(produto);
		db.commit();
	}

	public void crearReclamo(String descripcion, String direccion, String nCategoria) {
		Categoria categoria = categoriaDAO.buscarPorCategoria(db, nCategoria);
		Reclamo reclamo = new Reclamo(descripcion, direccion, categoria);
		activo.realizarReclamo(reclamo);
		db.commit();
	}

	public void crearCategoria(String nCategoria, String descripcion, int puntos) {
		Categoria categoria = new Categoria(nCategoria, descripcion, puntos);
		categoriaDAO.salvar(db, categoria);

	}

	public void agregarEvento() {

	}

	// METODOS NO ESENCIALES - SOLO PARA PROBAR

	public void listarCiudadanos() {
		ObjectSet<Ciudadano> ciudadanos = ciudadanoDAO.buscarTodos(db);

		for (Ciudadano ciudadano : ciudadanos) {
			System.out.println("Nombre: " + ciudadano.getNombre() + " Apellido: " + ciudadano.getApellido()
					+ " Correo: " + ciudadano.getEmail());
		}
	}

	public void listarReclamos() {
		ObjectSet<Reclamo> reclamos = reclamosDAO.buscarTodos(db);

		for (Reclamo reclamo : reclamos) {
			System.out.println("Reclamo");
			System.out.println("  Fecha: " + reclamo.getFecha().toString());
			System.out.println("  Descripcion: " + reclamo.getDescripcion());
			System.out.println("  Direccion: " + reclamo.getDireccion());
			System.out.println("  Categoria: " + reclamo.getCategoria().toString());
			System.out.println("Eventos:");
			List<Evento> eventos = reclamo.getEventos();
			int i = 1;
			for (Evento evento : eventos) {
				System.out.println(i++ + "- Fecha: " + evento.getFecha().toString() + " " + evento.getDescripcion());
			}
		}
	}

	public void existeCiudadano(int dni) {

		Ciudadano ciudadano = buscarCiudadano(dni);
		if (ciudadano == null) {
			System.out.println("No Existe");
		} else {
			System.out.println("Existe");
		}
	}

}
