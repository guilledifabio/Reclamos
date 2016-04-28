package dao;

import java.io.Serializable;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

import modelo.Canje;




public class CanjeDaoImpl  extends GenericDaoImpl<Canje, Serializable> implements CanjeDao {
	
	

	public List<Canje> ListarCanjesConDescripcion(ObjectContainer db, final String desc) {
	List <Canje> pilots = db.query(new Predicate<Canje>() {
		public boolean match(Canje canje) {
		return canje.getDescripcion().equals(desc);
		}
		});
return pilots;
}
	}
