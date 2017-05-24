package pl.com.tt.projectmanagementsystem.persistence.sql;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pl.com.tt.projectmanagementsystem.persistence.Persistable;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;

public class SQLPersistenceManager implements PersistenceManager {
	
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	
	private SQLPersistenceManager(){
		
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public static PersistenceManager getInstance() {
		SQLPersistenceManager sqlPersistenceManager = new SQLPersistenceManager();
		sqlPersistenceManager.setEntityManagerFactory(Persistence.createEntityManagerFactory("projectmanagementsystem"));
		sqlPersistenceManager.setEntityManager(sqlPersistenceManager.getEntityManagerFactory().createEntityManager());
		return sqlPersistenceManager;
	}

	@Override
	public void create(Persistable persistable) {
		EntityManager entityManager = this.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(persistable);
		entityManager.getTransaction().commit();
	}
	
	@Override
	public Persistable get(Persistable persistable) {
		EntityManager entityManager = this.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.find(persistable.getClass(), persistable.getId());
		entityManager.getTransaction().commit();
		return null;
	}

	@Override
	public void update(Persistable persistable) {
		EntityManager entityManager = this.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.refresh(persistable);
		entityManager.getTransaction().commit();
	}
	

	@Override
	public Persistable delete(Persistable persistable) {
		EntityManager entityManager = this.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(persistable);
		entityManager.getTransaction().commit();
		return null;
	}

}
