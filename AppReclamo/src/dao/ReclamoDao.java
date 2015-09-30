package dao;

import java.util.List;

import modelo.Reclamo;


public interface ReclamoDao {
	// public abstract void borrar(Reclamo rec);

	public abstract void salvar(Reclamo rec);

	public abstract List<Reclamo> buscarTodos();
}