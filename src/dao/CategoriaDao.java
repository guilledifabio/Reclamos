package dao;

import com.db4o.ObjectContainer;

public interface CategoriaDao {

	public void modificar(ObjectContainer db, final String nombreactual, String nombrenuevo, String desc, int puntos);

	public void eliminarPorNombre(ObjectContainer db, final String nombre);
}
