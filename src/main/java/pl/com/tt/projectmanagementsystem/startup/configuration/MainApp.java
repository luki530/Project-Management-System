package pl.com.tt.projectmanagementsystem.startup.configuration;

import java.util.Collection;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.actions.implementations.CreateNewProjectRoleAction;
import pl.com.tt.projectmanagementsystem.actions.implementations.CreateProjectAction;
import pl.com.tt.projectmanagementsystem.actions.implementations.CreateUserAction;
import pl.com.tt.projectmanagementsystem.actions.implementations.DeleteProjectAction;
import pl.com.tt.projectmanagementsystem.actions.implementations.ListProjectsAction;
import pl.com.tt.projectmanagementsystem.actions.implementations.LoginAction;
import pl.com.tt.projectmanagementsystem.actions.implementations.LogoutAction;
import pl.com.tt.projectmanagementsystem.actions.implementations.NewDocumentAction;
import pl.com.tt.projectmanagementsystem.actions.implementations.ProjectDetailsAction;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;
import pl.com.tt.projectmanagementsystem.userInterface.UserInterface;
import pl.com.tt.projectmanagementsystem.xmlUtil.XmlWatcher;

public class MainApp implements Runnable {

	private UserInterface userInterface;
	private PersistenceManager persistenceManager;
	private Collection<Action> actions;
	private XmlWatcher xmlWatcher;
	private boolean run;

	public MainApp(MainConfiguration appConfig) {
		userInterface = appConfig.getUserInterface();
		persistenceManager = appConfig.getPersistenceManager();
		actions = appConfig.getActions();
		xmlWatcher = appConfig.getXmlWatcher();
		run = true;
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

	@Override
	public void run() {
		Thread xmlWatcherThread = new Thread(xmlWatcher);
		xmlWatcherThread.start();
		while (run) {
			Action action = userInterface.getActionFromQueue();
			if (action != null) {
				ActionResult actionResult = dispatchAction(action);
				userInterface.addActionResultToQueue(actionResult);
			}
		}
	}

	private ActionResult dispatchAction(Action action) {
		String actionClass = action.getClass().getSimpleName();
		switch (actionClass) {
		case "LoginAction":
			LoginAction loginAction = (LoginAction) action;
			loginAction.setParameter("persistenceManager", persistenceManager);
			return loginAction.doOperation();
		case "ListProjectsAction":
			ListProjectsAction listProjectsAction = (ListProjectsAction) action;
			listProjectsAction.setParameter("persistenceManager", persistenceManager);
			return listProjectsAction.doOperation();
		case "LogoutAction":
			LogoutAction logoutAction = (LogoutAction) action;
			return logoutAction.doOperation();
		case "CreateProjectAction":
			CreateProjectAction createProjectAction = (CreateProjectAction) action;
			createProjectAction.setParameter("persistenceManager", persistenceManager);
			return createProjectAction.doOperation();
		case "CreateUserAction":
			CreateUserAction createUserAction = (CreateUserAction) action;
			createUserAction.setParameter("persistenceManager", persistenceManager);
			return createUserAction.doOperation();
		case "ProjectDetailsAction":
			ProjectDetailsAction projectDetailsAction = (ProjectDetailsAction) action;
			projectDetailsAction.setParameter("persistenceManager", persistenceManager);
			return projectDetailsAction.doOperation();
		case "CreateNewProjectRoleAction":
			CreateNewProjectRoleAction createNewProjectRoleAction = (CreateNewProjectRoleAction) action;
			createNewProjectRoleAction.setParameter("persistenceManager", persistenceManager);
			return createNewProjectRoleAction.doOperation();
		case "DeleteProjectAction":
			DeleteProjectAction deleteProjectAction = (DeleteProjectAction) action;
			deleteProjectAction.setParameter("persistenceManager", persistenceManager);
			return deleteProjectAction.doOperation();
		case "NewDocumentAction":
			NewDocumentAction newDocumentAction = (NewDocumentAction) action;
			newDocumentAction.setParameter("persistenceManager", persistenceManager);
			return newDocumentAction.doOperation();

		default:
			return null;
		}
	}

}
