package pl.com.tt.projectmanagementsystem.actions.implementations;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.appContext.AppContext;
import pl.com.tt.projectmanagementsystem.databaseModel.Project;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;

public class CreateProjectAction extends Action {

	@Override
	public ActionResult doOperation() {

		try {
			PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");
			String projectTitle = (String) getParameter("projectTitle");
			String projectDescription = (String) getParameter("projectDescription");
			Project project = new Project();
			project.setTitle(projectTitle);
			project.setDescription(projectDescription);
			project.setUser(AppContext.getLoggedUser());
			persistenceManager.create(project);
			return new ActionResult("OK");
		} catch (Exception e) {
			e.printStackTrace();
			return new ActionResult("ERROR");
		}

	}

}
