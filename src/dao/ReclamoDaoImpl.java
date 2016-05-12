package dao;

import java.io.Serializable;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import modelo.Reclamo;

public class ReclamoDaoImpl  extends GenericDaoImpl<Reclamo, Serializable> implements ReclamoDao {
	
	public Reclamo buscarPorId(ObjectContainer db, final String id) {

		List<Reclamo> lreclamo = db.query(new Predicate<Reclamo>() {
			public boolean match(Reclamo reclamo) {
				return reclamo.getId().equals(id);
			}
		});

		if (lreclamo.size() == 0) {
			return null;
		}
		Reclamo reclamo = lreclamo.get(0);

		return reclamo;
	}
}
