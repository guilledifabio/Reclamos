package dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import modelo.Ciudadano;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.Db4oException;
import com.db4o.query.Predicate;
import com.db4o.query.Query;





public abstract class GenericDaoImpl<T, Id extends Serializable> implements
		GenericDao<T, Id> {

	private Class<T> claseDePersistencia;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		this.claseDePersistencia = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	
	/*public T buscarPorClave(Id id) {

		ObjectContainer bd= Helper.ConnectionDB("reclamos");		
		T objeto = null;
		try {
			
			return objeto;
		} finally {
			
		}
		
	}
*/

	public ObjectSet<T> buscarTodos(ObjectContainer db) {
		
		Query q = db.query();
		q.constrain(claseDePersistencia);
		ObjectSet result = q.execute();
			
		return result;
		
	}

	
/*
	public void borrar(T objeto) {
		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = manager.getTransaction();
			tx.begin();
			manager.remove(manager.merge(objeto));

			tx.commit();
		} catch (PersistenceException e) {
			tx.rollback();
			throw e;
		} finally {
			manager.close();
		}
	}*/

	
	public void insertar(ObjectContainer db,T objeto) {
		
		try {
		db.store(objeto);
		System.out.println("Guardado " + objeto);
		} catch (Db4oException  e) {
			System.out.println("Error al guardar : " + e.getMessage());
		} 

	}
}