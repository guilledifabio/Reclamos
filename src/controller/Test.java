package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import modelo.Canje;
import modelo.Catalogo;
import modelo.Categoria;
import modelo.Ciudadano;
import modelo.Evento;
import modelo.Producto;

import modelo.Reclamo;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import dao.CanjeDaoImpl;
import dao.CatalogoDaoImpl;
import dao.CategoriaDao;
import dao.CategoriaDaoImpl;
import dao.CiudadanoDaoImpl;
import dao.Helper;
import dao.ProductoDao;
import dao.ProductoDaoImpl;
import dao.ReclamoDaoImpl;

public class Test {

	public static void main(String[] args) {
		ObjectContainer db = Helper.ConnectionDB("reclamos");

		// catdao.salvar(catalogo); Error con los que tienen array list!!!

		// ObjectSet<Catalogo> catalogos = catdao.buscarTodos(db);
		// CREATE

		try {

			// ----------------------------- CRUD
			// Categorias------------------------------------

			Categoria cate = new Categoria("Calles y Veredas", "Solicitá el arreglo de veredas en mal estado o baches",
					10);
			Categoria cate2 = new Categoria("Arbolado", "Solicitá la poda de ramas y despejes", 9);
			Categoria cate3 = new Categoria("Alumbrado", "Solicitá el arreglo o la falta de luces", 5);
			Categoria cate4 = new Categoria("Red de agua potable", "Solicitá el arreglo de cañerias dañadas", 15);
			/*
			 * db.store(cate); db.store(cate2); db.store(cate3);
			 */
			CategoriaDaoImpl categoriadao = new CategoriaDaoImpl();
			categoriadao.insertar(db, cate);
			categoriadao.insertar(db, cate2);
			categoriadao.insertar(db, cate3);
			categoriadao.modificar(db, "Alumbrado", "Alumbrado Modificado", "Solicitá el arreglo o la falta de luces",
					7);
			categoriadao.eliminarPorNombre(db, "Arbolado");

			// CRUD CATALOGO

			CatalogoDaoImpl catdao = new CatalogoDaoImpl();
			Catalogo catalogo = new Catalogo("catalogo nuevo");

			// CRUD Producto
			Producto prod = new Producto("Notebook", 30);
			List<Producto> productos = new ArrayList<Producto>();
			//productos.add(prod);
			catalogo.setProductos(productos);
			catalogo.agregarProducto(new Producto("Nuevo producto", 30));
			catdao.insertar(db, catalogo);
			System.out.println(catalogo.toString());
			ProductoDaoImpl prodao = new ProductoDaoImpl();
			// prodao.salvar(prod);

			CanjeDaoImpl candao = new CanjeDaoImpl();
			// Crear el Canje de un Producto - Agregar al ciudadano

			Canje can = new Canje("27/09/2015");
			Canje can1 = new Canje("28/09/2015");
			can.setProducto(prod);
			// prodao.eliminar(db, prod);
			// db.store(can);
			candao.insertar(db, can);
			/*
			 * String desc = "mal estacionado"; List<Canje> canjes =
			 * candao.ListarCanjesConDescripcion(db, desc);
			 * 
			 * System.out.println("Canjes con descripcion : " + desc);
			 * 
			 * Iterator<Canje> Iteratorr = canjes.iterator(); while
			 * (Iteratorr.hasNext()) { Canje canje = Iteratorr.next();
			 * System.out.print("Canje : " + canje.getDescripcion() + " - ");
			 * System.out.println(canje.getFecha() + "  "); }
			 */

			// ----------------------------- CRUD
			// CIUDADANO------------------------------------
			CiudadanoDaoImpl ciudao = new CiudadanoDaoImpl();
			Ciudadano ciu = new Ciudadano("Guillermo", "Difabio", 36849832, "guillermodifabio@gmail.com", 30);
			Ciudadano ciu2 = new Ciudadano("Roberto", "Perez", 38567432, "robertoperez@gmail.com", 10);
			ciu.CanjearPuntos(prod);
			// ACA
			Reclamo reclamo2 = new Reclamo("02/05/2015", "Falta de Agua ", "Barrio Guido");

			reclamo2.setCategoria(cate4);
			ciu.realizarReclamo(reclamo2); // REALIZAR UN RECLAMO
			ciudao.insertar(db, ciu);
			ciudao.insertar(db, ciu2);
			List<Ciudadano> ciudadanos = ciudao.buscarTodos(db);

			Iterator<Ciudadano> Iteratorciu = ciudadanos.iterator();
			while (Iteratorciu.hasNext()) {
				Ciudadano ciud = Iteratorciu.next();
				System.out.println("Ciudadano : " + ciud.getNombre() + " " + ciud.getApellido() + " - ");
				List<Canje> ciucanjes = ciud.getCanjes();
				Iterator<Canje> Iteratorciucanjes = ciucanjes.iterator();
				while (Iteratorciucanjes.hasNext()) {
					Canje ca = Iteratorciucanjes.next();
					System.out.println(ca.toString());
					/*
					 * System.out.print("Canje : " + ca.getFecha() + " - ");
					 * System.out.println(ca.getProducto().getNombre());
					 */
				}
			}

			// RECLAMO
			List<Evento> eventos = new ArrayList<Evento>();
			Reclamo rec = new Reclamo("27/09/2015", "Falta de Agua ", "Barrio Guido");
			Evento event1 = new Evento("10/09/2014", "Reclamo Iniciado");
			Evento event2 = new Evento("11/09/2014", "Se contacto a la empresa contratista");
			Evento event3 = new Evento("12/09/2014",
					"La empresa contratista nos informa que necesita realizar un cambio en una tubería");
			eventos.add(event1);
			eventos.add(event2);
			eventos.add(event3);
			rec.setEventos(eventos);
			List<Evento> eventos2 = new ArrayList<Evento>();
			Reclamo rec2 = new Reclamo("27/09/2015", "Corte de Electricidad ", "Barrio Lavalle");
			Evento event4 = new Evento("10/09/2014", "Reclamo Iniciado");
			Evento event5 = new Evento("11/09/2014", "Se contacto a la empresa contratista");
			Evento event6 = new Evento("12/09/2014",
					"La empresa contratista nos informa que necesita realizar un cambio de cable");
			eventos2.add(event4);
			eventos2.add(event5);
			eventos2.add(event6);
			rec2.setEventos(eventos2);

			db.store(rec);
			db.store(rec2);
			ReclamoDaoImpl recdao = new ReclamoDaoImpl();
			// GUARDAR
			/*
			 * recdao.salvar(rec); ciudao.salvar(ciu); ciudao.salvar(ciu2);
			 */

			// 1. Categorias

			// 2. Listar Ciudadanos ... Hay que cargar canjes

			List<Ciudadano> ciudadanoss = ciudao.buscarTodos(db);

			for (Ciudadano c : ciudadanoss) // Esto es un for extendido o
											// for-each
			{
				System.out.println("Ciudadano " + c.getNombre() + " " + c.getApellido());
				List<Canje> canjes = c.getCanjes();
				for (Canje ca : canjes) {
					System.out.println("Canje " + ca.getFecha() + ca.getProducto().getNombre());
				}
			}

			// 7. LISTAR RECLAMOS CON SUS EVENTOS
			List<Reclamo> reclamos = recdao.buscarTodos(db);
			Iterator<Reclamo> Iterator = reclamos.iterator();
			while (Iterator.hasNext()) {
				Reclamo reclamo = Iterator.next();
				System.out.println("Reclamo : " + reclamo.getDescripcion() + " / ");
				Iterator<Evento> Iteratorevento = rec.getEventos().iterator();
				int i = 1;
				while (Iteratorevento.hasNext()) {
					Evento evento = Iteratorevento.next();
					System.out.println(i + " Fecha : " + evento.getFecha() + " - " + evento.getDescripcion());
					i++;
				}
			}
		} finally {
			db.close();
		}

	}

}
