package pl.com.tt.projectmanagementsystem.startup.configuration;

import java.util.Collection;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;
import pl.com.tt.projectmanagementsystem.userInterface.UserInterface;


public class ProjectManagementSystem {
	
	private static UserInterface userInterface;
	private static PersistenceManager persistenceManager;
	private static Collection<Action> actions;
	
	public ProjectManagementSystem(MainConfiguration appConfig) {
		userInterface = appConfig.getUserInterface();
		persistenceManager = appConfig.getPersistenceManager();
		actions = appConfig.getActions();
	}
		
	
	
	
	public static UserInterface getUserInterface() {
		return userInterface;
	}






	public static void setUserInterface(UserInterface userInterface) {
		ProjectManagementSystem.userInterface = userInterface;
	}






	public static PersistenceManager getPersistenceManager() {
		return persistenceManager;
	}






	public static void setPersistenceManager(PersistenceManager persistenceManager) {
		ProjectManagementSystem.persistenceManager = persistenceManager;
	}






	public static Collection<Action> getActions() {
		return actions;
	}






	public static void setActions(Collection<Action> actions) {
		ProjectManagementSystem.actions = actions;
	}






	public void runApp(){
		
	}

}
