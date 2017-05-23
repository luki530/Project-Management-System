package pl.com.tt.projectmanagementsystem.persistence;

public interface PersistenceManager {
	
	
	public Persistable create(Persistable persistable);
	
	public Persistable get(Persistable persistable);
	
	public Persistable update(Persistable persistable);
	
	public Persistable delete(Persistable persistable);	

}
