package dao;

import com.db4o.ObjectContainer;

import modelo.Reclamo;


public interface ReclamoDao {
	
	public abstract Reclamo buscarPorId(ObjectContainer db, String id);
}
