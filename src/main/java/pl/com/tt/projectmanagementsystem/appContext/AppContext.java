package pl.com.tt.projectmanagementsystem.appContext;

import pl.com.tt.projectmanagementsystem.databaseModel.Document;
import pl.com.tt.projectmanagementsystem.databaseModel.Project;
import pl.com.tt.projectmanagementsystem.databaseModel.User;

public class AppContext {
	
	private static User loggedUser = null;
	private static Project currentProject = null;
	private static Document currentDocument = null;
	
	public static Document getCurrentDocument() {
		return currentDocument;
	}
	public static void setCurrentDocument(Document currentDocument) {
		AppContext.currentDocument = currentDocument;
		AppContext.getLoggedUser().refreshPermissions();
	}
	public static User getLoggedUser() {
		return loggedUser;
	}
	public void setLoggedUser(User loggedUser) {
		AppContext.loggedUser=loggedUser;
		AppContext.getLoggedUser().refreshPermissions();
	}
	public static Project getCurrentProject() {
		return currentProject;
	}
	
	public static void setCurrentProject(Project currentProject) {
		AppContext.currentProject = currentProject;
		AppContext.getLoggedUser().refreshPermissions();
	}
	
}
