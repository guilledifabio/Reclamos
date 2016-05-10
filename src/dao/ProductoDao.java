package dao;

import java.util.List;

import com.db4o.ObjectContainer;

import modelo.Producto;



public interface ProductoDao {
	 
	public abstract Producto buscar(ObjectContainer db, String nombre);

}
