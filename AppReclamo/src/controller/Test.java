package controller;

import modelo.Canje;
import modelo.Catalogo;
import modelo.Ciudadano;
import modelo.Producto;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ta.TransparentPersistenceSupport;

import dao.CanjeDaoImpl;
import dao.CatalogoDaoImpl;

public class Test {

	public static void main(String[] args) {


		CatalogoDaoImpl catdao= new CatalogoDaoImpl();
		Catalogo catalogo= new Catalogo("catalogo nuevo", null);
		catdao.salvar(catalogo);
		
	ObjectSet<Catalogo>	cat =catdao.buscarTodos();
	Producto prod= new Producto(1, "Notebook", 20);
	CanjeDaoImpl candao= new CanjeDaoImpl();
	Canje can= new Canje("mal estacionado", "27/09/2015", prod);
	candao.salvar(can);
	ObjectSet<Canje> canj=candao.buscarTodos();
	
	/*
		Ciudadano ciu = new Ciudadano("Guillermo", "Difabio", 36849832,
				"guillermodifabio@gmail.com", 3, null);
		for (int i = 0; i < 3; i++) {
			 car.snapshot();
		}
		db.store(ciu);
	db.close();
	
*/
	}

}
