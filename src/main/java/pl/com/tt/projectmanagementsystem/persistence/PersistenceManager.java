package pl.com.tt.projectmanagementsystem.persistence;

import java.util.Collection;
import java.util.List;

public interface PersistenceManager {
	
	
	public void create(Persistable persistable);
	
	public void update(Persistable persistable);
	
	public Persistable delete(Persistable persistable);

	public Persistable get(Persistable persistable);

	public Collection<Persistable> findAll(Class modelClass);


}
