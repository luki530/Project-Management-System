package pl.com.tt.projectmanagementsystem.startup.configuration;

import java.util.Collection;
import java.util.LinkedList;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;
import pl.com.tt.projectmanagementsystem.persistence.sql.JPAPersistenceManager;
import pl.com.tt.projectmanagementsystem.userInterface.UserInterface;
import pl.com.tt.projectmanagementsystem.userInterface.gui.GraphicsUserInterface;
import pl.com.tt.projectmanagementsystem.xmlUtil.XmlWatcher;

public class MainConfiguration {

	private UserInterface userInterface;
	private PersistenceManager persistenceManager;
	private XmlWatcher xmlWatcher;

	public XmlWatcher getXmlWatcher() {
		return xmlWatcher;
	}

	public void setXmlWatcher(XmlWatcher xmlWatcher) {
		this.xmlWatcher = xmlWatcher;
	}

	private Collection<Action> actions;

	private MainConfiguration() {

	}

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

	public static MainConfiguration createDevelopmentConfigWithGui() {
		MainConfiguration config = new MainConfiguration();
		//PersistenceManager persistenceManager = JPAPersistenceManager.getInstance();
		//config.setPersistenceManager(persistenceManager);
		config.setActions(initializeActions());
		return config;
	}

	private static Collection<Action> initializeActions() {

		Collection<Action> actions = new LinkedList<Action>();
		// actions.add(new ShowProjectsAction());
		// actions.add(new CreateDocumentAction());

		return actions;

	}

}
