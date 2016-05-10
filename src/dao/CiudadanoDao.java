package dao;

import java.util.List;



import com.db4o.ObjectContainer;

import modelo.Catalogo;
import modelo.Ciudadano;

public interface CiudadanoDao {
		
		
		public Ciudadano buscarPorDni(ObjectContainer db, final int dni);

}
