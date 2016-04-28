package dao;

import java.util.List;

import com.db4o.ObjectContainer;

import modelo.Categoria;


public interface CategoriaDao {
	// public abstract void borrar(Categoria cat);

		
		public abstract List<Categoria> buscarTodos(ObjectContainer db);

		public abstract void insertar(ObjectContainer db,Categoria objeto);
}
