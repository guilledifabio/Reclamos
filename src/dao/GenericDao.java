package dao;

import java.io.Serializable;
import java.util.List;

import com.db4o.ObjectContainer;

public interface GenericDao<T, Id extends Serializable> {

	// T buscarPorClave(Id id);

	List<T> buscarTodos(ObjectContainer db);

	void salvar(ObjectContainer db, T objeto);

	void borrar(ObjectContainer db, T objeto);
}
