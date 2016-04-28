package dao;

import java.io.Serializable;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import modelo.Canje;
import modelo.Ciudadano;

public class CiudadanoDaoImpl extends GenericDaoImpl<Ciudadano, Serializable>
		implements CiudadanoDao {


	public void  ListarCiudadanos(ObjectContainer db) {
		List<Ciudadano> ciudadanos = buscarTodos(db);

		for (Ciudadano c : ciudadanos) // Esto es un for extendido o for-each
		{
			System.out.println("Ciudadano " + c.getNombre() + " "
					+ c.getApellido());
			List<Canje> canjes = c.getCanjes();
			for (Canje ca : canjes) {
				System.out.println("Canje " + ca.getDescripcion()
						+ ca.getFecha());
			}
		}

	}
}
