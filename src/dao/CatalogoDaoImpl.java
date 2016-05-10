package dao;

import modelo.Catalogo;


import java.io.Serializable;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ext.Db4oException;
import com.db4o.query.Predicate;


public class CatalogoDaoImpl extends GenericDaoImpl<Catalogo, Serializable> implements CatalogoDao {


	public Catalogo buscarPorNombre(ObjectContainer db, final String nombre) {
		try {
			List<Catalogo> result = db.query(new Predicate<Catalogo>() {
				public boolean match(Catalogo cat) {
					return cat.getNombre().equals(nombre);
				}
			});
			Catalogo found = (Catalogo) result.get(0);
			System.out.println("Se elimino correctamente la Categoria: " + nombre);
			return found;
			
		} catch (Db4oException e) {
			System.out.println("No se pudo eliminar la Categoria, Error: " + e.getMessage());
			return null;
		}
		
	}

}
