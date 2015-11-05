package dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import modelo.Ciudadano;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
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
		q.constrain(Ciudadano.class);
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

	public void salvar(T objeto) {
		
		
		System.out.println("Guardado " + objeto);
		
		
	}
/*
	public void insertar(T objeto) {
		EntityManagerFactory factoriaSession = JPAHelper.getJPAFactory();
		EntityManager manager = factoriaSession.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = manager.getTransaction();
			tx.begin();
			manager.persist(objeto);
			tx.commit();
		} catch (PersistenceException e) {
			tx.rollback();
			throw e;
		} finally {
			manager.close();
		}

	}*/
}