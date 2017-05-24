package pl.com.tt.projectmanagementsystem.persistence;

public interface PersistenceManager {
	
	
	public void create(Persistable persistable);
	
	public void update(Persistable persistable);
	
	public Persistable delete(Persistable persistable);

	public Persistable get(Persistable persistable);


}
