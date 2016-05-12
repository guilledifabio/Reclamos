package dao;

import java.io.Serializable;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import modelo.Canje;

public class CanjeDaoImpl extends GenericDaoImpl<Canje, Serializable> implements CanjeDao {

	public List<Canje> ListarCanjesConFecha(ObjectContainer db, final String fecha) {

		List<Canje> canjes = db.query(new Predicate<Canje>() {
			public boolean match(Canje canje) {
				return canje.getFecha().equals(fecha);
			}
		});
		return canjes;

	}
	
	public Canje buscarPorId(ObjectContainer db, final String id) {
		// TODO Auto-generated method stub
		List<Canje> result = db.query(new Predicate<Canje>() {
			@Override
			public boolean match(Canje canje) {
				return canje.getId().equals(id);
			}
		});
		return result.get(0);
	}
}
