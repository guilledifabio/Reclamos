package dao;

import java.util.List;

import com.db4o.ObjectContainer;

import modelo.Evento;

public interface EventoDao {
	// public abstract void borrar(Evento even);

	public abstract void insertar(ObjectContainer db,Evento objeto);

		public abstract List<Evento> buscarTodos(ObjectContainer db);


}
