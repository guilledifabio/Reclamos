package dao;

import java.io.Serializable;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.ext.Db4oException;
import com.db4o.query.Predicate;

import modelo.Categoria;

public class CategoriaDaoImpl extends GenericDaoImpl<Categoria, Serializable> implements CategoriaDao {

	public void modificar(ObjectContainer db, final String nombreactual, String nombrenuevo, String desc, int puntos) {
		try {
			List<Categoria> result = db.query(new Predicate<Categoria>() {
				public boolean match(Categoria cat) {
					return cat.getNombre().equals(nombreactual);
				}
			});
			Categoria found = (Categoria) result.get(0);
			found.setNombre(nombrenuevo);
			found.setDescripcion(desc);
			found.setPuntos(puntos);
			db.store(found);
			System.out.println("Modifacacion correcta");
		} catch (Db4oException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

	public void eliminar(ObjectContainer db, final String nombre) {
		try {
			List<Categoria> result = db.query(new Predicate<Categoria>() {
				public boolean match(Categoria cat) {
					return cat.getNombre().equals(nombre);
				}
			});
			Categoria found = (Categoria) result.get(0);
			db.delete(found);
			System.out.println("Se elimino correctamente la categoria: "+nombre);
		} catch (Db4oException e) {
			System.out.println("No se pudo eliminar la Categoria, Error: " + e.getMessage());
		}

	}

	
}
