package controller;

import java.util.ArrayList;
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
			CatalogoDaoImpl catdao = new CatalogoDaoImpl();
			Catalogo catalogo = new Catalogo("catalogo nuevo");
			catdao.insertar(db,catalogo);

			// ----------------------------- CRUD Categorias------------------------------------

			Categoria cate = new Categoria("Calles y Veredas", "Solicitá el arreglo de veredas en mal estado o baches",
					10);
			Categoria cate2 = new Categoria("Arbolado", "Solicitá la poda de ramas y despejes", 9);
			Categoria cate3 = new Categoria("Alumbrado", "Solicitá el arreglo o la falta de luces", 5);
			/*
			 * db.store(cate); db.store(cate2); db.store(cate3);
			 */
			CategoriaDaoImpl categoriadao = new CategoriaDaoImpl();
			categoriadao.insertar(db, cate);
			categoriadao.insertar(db, cate2);
			categoriadao.insertar(db, cate3);
			categoriadao.modificar(db, "Alumbrado", "Alumbrado Modificado", "Solicitá el arreglo o la falta de luces",
					7);
			categoriadao.eliminar(db, "Arbolado");

			// CRUD Producto
			Producto prod = new Producto(8, "Notebook", 30);
			ProductoDaoImpl prodao = new ProductoDaoImpl();
			// prodao.salvar(prod);

			CanjeDaoImpl candao = new CanjeDaoImpl();
			// Crear el Canje de un Producto

			Canje can = new Canje("mal estacionado", "27/09/2015");
			can.setProducto(prod);

			// db.store(can);
			candao.insertar(db, can);
			String desc = "mal estacionado";
			List<Canje> canjes = candao.ListarCanjesConDescripcion(db, desc);

			System.out.println("Canjes con descripcion : " + desc);

			Iterator<Canje> Iteratorr = canjes.iterator();
			while (Iteratorr.hasNext()) {
				Canje canje = Iteratorr.next();
				System.out.print("Canje : " + canje.getDescripcion() + " - ");
				System.out.println(canje.getFecha() + "  ");
			}

			// CIUDADANO
			Ciudadano ciu = new Ciudadano("Guillermo", "Difabio", 36849832, "guillermodifabio@gmail.com", 3);
			Ciudadano ciu2 = new Ciudadano("Roberto", "Perez", 38567432, "robertoperez@gmail.com", 10);
			db.store(ciu);
			db.store(ciu2);
			CiudadanoDaoImpl ciudao = new CiudadanoDaoImpl();

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
			ciudao.ListarCiudadanos(db);

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
