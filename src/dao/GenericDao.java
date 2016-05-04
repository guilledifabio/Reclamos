package dao;

import java.io.Serializable;
import java.util.List;

import com.db4o.ObjectContainer;

public interface GenericDao<T, Id extends Serializable> {
	
	//T buscarPorClave(Id id);

	List<T> buscarTodos(ObjectContainer db);

	 void insertar(ObjectContainer db,T objeto);
	//void modificar(ObjectContainer db,T objeto);
	 void eliminar(ObjectContainer db,T objeto);
	
}
