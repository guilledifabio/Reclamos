package dao;

import java.util.List;



import com.db4o.ObjectContainer;

import modelo.Ciudadano;

public interface CiudadanoDao {
	// public abstract void borrar(Ciudadano ciu);

		public abstract void salvar(Ciudadano ciu);

		public abstract List<Ciudadano> buscarTodos(ObjectContainer db);


}
