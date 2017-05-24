package pl.com.tt.projectmanagementsystem.persistence;

import java.util.Collection;
import java.util.List;

public interface PersistenceManager {
	
	
	public void create(Persistable persistable);
	
	public void update(Persistable persistable);
	
	public Persistable delete(Persistable persistable);

	public Persistable find(Persistable persistable, long id);

	public List<Persistable> findAll(Persistable persistable);

}
