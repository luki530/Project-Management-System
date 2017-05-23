package pl.com.tt.projectmanagementsystem.startup.configuration;

import java.util.Collection;
import java.util.LinkedList;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;
import pl.com.tt.projectmanagementsystem.persistence.sql.SQLPersistenceManager;
import pl.com.tt.projectmanagementsystem.userInterface.UserInterface;
import pl.com.tt.projectmanagementsystem.userInterface.graphicUI.GraphicUserInterface;

public class MainConfiguration {

	private UserInterface userInterface;
	private PersistenceManager persistenceManager;
	private Collection<Action> actions;
	public UserInterface getUserInterface() {
		return userInterface;
	}
	public void setUserInterface(UserInterface userInterface) {
		this.userInterface = userInterface;
	}
	public PersistenceManager getPersistenceManager() {
		return persistenceManager;
	}
	public void setPersistenceManager(PersistenceManager persistenceManager) {
		this.persistenceManager = persistenceManager;
	}
	public Collection<Action> getActions() {
		return actions;
	}
	public void setActions(Collection<Action> actions) {
		this.actions = actions;
	}
	
	public static MainConfiguration createDevelopmentConfig() {

		MainConfiguration config = new MainConfiguration();
		UserInterface userInterface = GraphicUserInterface.getInstance();
		PersistenceManager persistenceManager = SQLPersistenceManager.getInstance();
		
		
		
		config.setUserInterface(userInterface);
		config.setPersistenceManager(persistenceManager);
		config.setActions(initializeActions());
		
		return config;
	}
	private static Collection<Action> initializeActions() {

		Collection<Action> actions = new LinkedList<Action>();
//		actions.add(new ShowProjectsAction());
//		actions.add(new CreateDocumentAction());

		return actions;

	}

	
}
