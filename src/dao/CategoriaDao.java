package dao;

import java.util.List;

import com.db4o.ObjectContainer;

import modelo.Catalogo;
import modelo.Categoria;


public interface CategoriaDao {
		
		public abstract Categoria buscarPorCategoria(ObjectContainer db, String id);

}
