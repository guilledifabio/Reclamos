package controller;


import modelo.Canje;
import modelo.Catalogo;
import modelo.Ciudadano;
import modelo.Producto;


import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;


import dao.CanjeDaoImpl;
import dao.CatalogoDaoImpl;
import dao.CiudadanoDaoImpl;
import dao.Helper;
import dao.ProductoDao;
import dao.ProductoDaoImpl;

public class Test {

	public static void main(String[] args) {
		ObjectContainer db = Helper.ConnectionDB("reclamos");
		

		CatalogoDaoImpl catdao = new CatalogoDaoImpl();
		Catalogo catalogo = new Catalogo("catalogo nuevo");
		//catdao.salvar(catalogo); Errro con los que tienen array list!!!

	//	ObjectSet<Catalogo> catalogos = catdao.buscarTodos(db);

		Producto prod = new Producto(8, "Notebook", 30);
		ProductoDaoImpl prodao = new ProductoDaoImpl();
		//prodao.salvar(prod);
	
		CanjeDaoImpl candao = new CanjeDaoImpl();
		// Crear el Canje de un Producto
		

		Canje can = new Canje("mal estacionado", "27/09/2015");
		//can.setProducto(prod);
		//candao.salvar(can);
		//ObjectSet<Canje> canj = candao.buscarTodos(db);

		Ciudadano ciu = new Ciudadano("Guillermo", "Difabio", 36849832,
				"guillermodifabio@gmail.com", 3);
		//CiudadanoDaoImpl ciudao = new CiudadanoDaoImpl();
		//ciudao.salvar(ciu);
		db.store(prod);
		db.store(can);
		db.store(ciu);
		db.store(catalogo);
		
		
		db.close();

	}

}
