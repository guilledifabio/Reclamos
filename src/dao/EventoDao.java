package dao;

import java.util.List;

import com.db4o.ObjectContainer;

import modelo.Evento;

public interface EventoDao {
	// public abstract void borrar(Evento even);

		public abstract void salvar(Evento even);

		public abstract List<Evento> buscarTodos(ObjectContainer db);


}
