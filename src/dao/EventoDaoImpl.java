package dao;

import java.io.Serializable;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import modelo.Evento;

public class EventoDaoImpl extends GenericDaoImpl<Evento, Serializable> implements EventoDao {
	
	public Evento buscarPorId(ObjectContainer db, final String id) {

		List<Evento> levento = db.query(new Predicate<Evento>() {
			public boolean match(Evento evento) {
				return evento.getId().equals(id);
			}
		});

		if (levento.size() == 0) {
			return null;
		}
		Evento evento = levento.get(0);

		return evento;
	}
	
}
