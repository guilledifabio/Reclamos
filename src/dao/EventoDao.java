package dao;

import com.db4o.ObjectContainer;

import modelo.Evento;

public interface EventoDao {
	
	public abstract Evento buscarPorId(ObjectContainer db, String id);
}
