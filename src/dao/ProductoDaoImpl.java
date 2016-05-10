package dao;

import java.io.Serializable;
import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;

import modelo.Producto;

public class ProductoDaoImpl extends GenericDaoImpl<Producto, Serializable> implements ProductoDao{

	public Producto buscar(ObjectContainer db, final String nombre) {

		List<Producto> lcatalogo = db.query(new Predicate<Producto>() {
			public boolean match(Producto producto) {
				return producto.getNombre().equals(nombre);
			}
		});

		if (lcatalogo.size() == 0) {
			return null;
		}
		Producto producto = lcatalogo.get(0);

		return producto;
	}

}
