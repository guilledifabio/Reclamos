package dao;

import com.db4o.ObjectContainer;

import modelo.Catalogo;

public interface CatalogoDao {
	// public abstract void borrar(Catalogo articulo);

	public Catalogo buscarPorNombre(ObjectContainer db, String nombre);

}
