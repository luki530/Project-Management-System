package pl.com.tt.projectmanagementsystem.appContext;

import pl.com.tt.projectmanagementsystem.databaseModel.Document;
import pl.com.tt.projectmanagementsystem.databaseModel.Project;
import pl.com.tt.projectmanagementsystem.databaseModel.User;

public class AppContext {
	
	private User loggedUser = null;
	private Project currentProject = null;
	private Document currentDocument = null;
	
	public Document getCurrentDocument() {
		return currentDocument;
	}
	public void setCurrentDocument(Document currentDocument) {
		this.currentDocument = currentDocument;
	}
	public User getLoggedUser() {
		return loggedUser;
	}
	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	public Project getCurrentProject() {
		return currentProject;
	}
	public void setCurrentProject(Project currentProject) {
		this.currentProject = currentProject;
	}
	
	
	
	
	
	

}
