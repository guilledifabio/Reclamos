package dao;

import java.io.Serializable;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import modelo.Ciudadano;

public class CiudadanoDaoImpl extends GenericDaoImpl<Ciudadano, Serializable> implements CiudadanoDao {

	public Ciudadano buscarPorDni(ObjectContainer db, final int dni) {

		List<Ciudadano> lciudadano = db.query(new Predicate<Ciudadano>() {
			public boolean match(Ciudadano ciudadano) {
				return ciudadano.getDni() == dni;
			}
		});
		if (lciudadano.size() == 0) {
			return null;
		}
		Ciudadano ciudadano = lciudadano.get(0);
		return ciudadano;
	}
}
