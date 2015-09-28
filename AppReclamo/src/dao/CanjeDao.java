package dao;

import java.util.List;

import modelo.Canje;

public interface CanjeDao {
	// public abstract void borrar(Canje can);

	public abstract void salvar(Canje can);

	public abstract List<Canje> buscarTodos();

}
