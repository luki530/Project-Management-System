package pl.com.tt.projectmanagementsystem.startup.configuration;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;
import pl.com.tt.projectmanagementsystem.userInterface.UserInterface;
import pl.com.tt.projectmanagementsystem.xmlUtil.XmlWatcher;

public class ProjectManagementSystem implements Runnable {

	private static UserInterface userInterface;
	private static PersistenceManager persistenceManager;
	private static Collection<Action> actions;
	private static XmlWatcher xmlWatcher;
	private Queue<Action> actionsQueue = new LinkedList<>();

	public ProjectManagementSystem(MainConfiguration appConfig) {
		userInterface = appConfig.getUserInterface();
		persistenceManager = appConfig.getPersistenceManager();
		actions = appConfig.getActions();
		xmlWatcher = appConfig.getXmlWatcher();
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

	@Override
	public void run() {
		Thread xmlWatcherThread = new Thread(xmlWatcher);
		xmlWatcherThread.start();
		for (;;) {
			if (!actionsQueue.isEmpty()) {
				Action action = actionsQueue.poll();
				
				ActionResult actionResult = action.doOperation();
				userInterface.addActionResult(actionResult);
			}
		}
	}
}
