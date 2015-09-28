package dao;

import java.util.List;

import modelo.Catalogo;


public interface CatalogoDao {
	//public abstract void borrar(Catalogo articulo);

	public abstract void salvar(Catalogo catalogo);

	public abstract List<Catalogo> buscarTodos();


}
