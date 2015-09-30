package dao;

import java.util.List;

import modelo.Categoria;


public interface CategoriaDao {
	// public abstract void borrar(Categoria cat);

		public abstract void salvar(Categoria cat);

		public abstract List<Categoria> buscarTodos();


}
