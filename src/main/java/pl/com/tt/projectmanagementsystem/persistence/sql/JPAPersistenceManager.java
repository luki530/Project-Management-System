package pl.com.tt.projectmanagementsystem.persistence.sql;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;

import pl.com.tt.projectmanagementsystem.persistence.Persistable;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;

public class JPAPersistenceManager implements PersistenceManager {

    private static final String PERSISTENCE_UNIT_NAME = "projectmanagementsystem";
    private static JPAPersistenceManager instance = new JPAPersistenceManager();
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	private JPAPersistenceManager() {
	    entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    entityManager = entityManagerFactory.createEntityManager();
	}
	
	public static PersistenceManager getInstance() {
	    return instance;
	}
	
	@Override
	public Persistable find(Persistable persistable, Object id) {
	    return entityManager.find(persistable.getClass(), id);
	}
	
    @Override
	public List<Persistable> findAll(Persistable persistable) {
	    List<Persistable> foundPersistables = new ArrayList<>();
	    Class<?> clazz = persistable.getClass();
	    Annotation[] annotations = clazz.getAnnotations();
	    assert(annotations != null);
	    String findAllQuery = "";
	    for (Annotation annotation : annotations) {
	        if (annotation.annotationType().equals(NamedQuery.class)) {
	            NamedQuery nq = (NamedQuery) annotation;
	            if (nq.name().contains("findAll")) { // <-- bo możemy mieć więcej niż jedną NamedQuery
	                findAllQuery = nq.query();
	            }
	        }
	    }
	    foundPersistables = (List<Persistable>) entityManager.createQuery(findAllQuery).getResultList();
	    return foundPersistables;
	}
	
	@Override
	public void create(Persistable persistable) {
	    beginTransaction();
	    entityManager.persist(persistable);
	    commitTransaction();
	}

	@Override
	public void update(Persistable persistable) {
	    beginTransaction();
	    entityManager.remove(persistable);
	    entityManager.persist(persistable);
	    commitTransaction();
	}

	@Override
	public Persistable delete(Persistable persistable) {
	    beginTransaction();
	    entityManager.remove(persistable);
	    commitTransaction();
		return null;
	}
	
	private void beginTransaction() {
	    entityManager.getTransaction().begin();
	}
	
	private void commitTransaction() {
	    entityManager.flush();
	    entityManager.getTransaction().commit();
	}
}
