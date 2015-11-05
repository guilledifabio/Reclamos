package api;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import dao.CiudadanoDaoImpl;
import modelo.Ciudadano;

public class Reclamos {
	
	

		private String nombreDB = "reclamos";
		private static ObjectContainer db;
			
			public void conectarDB() {
				db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), nombreDB);
			}
			
			public void desconectarDB() {
				db.close();
			}
			public Ciudadano buscarCiudadano(int DNI) {
		CiudadanoDaoImpl ciudadanoDAO = new CiudadanoDaoImpl();
		ObjectSet<Ciudadano> ciudadanos = ciudadanoDAO.buscarTodos(db);
		System.out.println("buscar ciudadano "+ciudadanos.size());
		for (Ciudadano ciudadano : ciudadanos) {
			if (ciudadano.getDni() == DNI) {
				return ciudadano;
			}
		}
	
		return null;
	}

}
