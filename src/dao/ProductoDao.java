package dao;

import java.util.List;

import com.db4o.ObjectContainer;

import modelo.Producto;



public interface ProductoDao {
	// public abstract void borrar(Canje can);

	public abstract void salvar(Producto pro);

	public abstract List<Producto> buscarTodos(ObjectContainer db);

}
