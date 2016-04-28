package dao;

import java.util.List;

import com.db4o.ObjectContainer;

import modelo.Catalogo;


public interface CatalogoDao {
	//public abstract void borrar(Catalogo articulo);

	
	public abstract List<Catalogo> buscarTodos(ObjectContainer db);
	
	public abstract void insertar(ObjectContainer db,Catalogo objeto);

}
