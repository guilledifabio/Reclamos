package dao;



import com.db4o.ObjectContainer;

import modelo.Producto;

public interface ProductoDao {
	public Producto buscarPorId(ObjectContainer db, final String id);

	public abstract Producto buscar(ObjectContainer db, String nombre);

}
