package dao;

import java.io.Serializable;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import modelo.Categoria;

public class CategoriaDaoImpl extends GenericDaoImpl<Categoria, Serializable> implements CategoriaDao{

	public Categoria buscarPorCategoria(ObjectContainer db, final String id) {

		List<Categoria> lcategoria = db.query(new Predicate<Categoria>() {
			public boolean match(Categoria categoria) {
				return categoria.getId().equals(id);
			}
		});

		if (lcategoria.size() == 0) {
			return null;
		}
		Categoria categoria = lcategoria.get(0);

		return categoria;
	}

}
