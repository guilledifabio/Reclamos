package dao;

import java.util.List;

import com.db4o.ObjectContainer;

import modelo.Canje;

public interface CanjeDao {

	public Canje buscarPorId(ObjectContainer db, final String id);

	public List<Canje> ListarCanjesConFecha(ObjectContainer db, String fecha);
}
