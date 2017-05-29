package pl.com.tt.projectmanagementsystem.actions.implementations;

import java.util.ArrayList;
import java.util.List;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.appContext.AppContext;
import pl.com.tt.projectmanagementsystem.databaseModel.ProjectRole;
import pl.com.tt.projectmanagementsystem.persistence.Persistable;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;

public class ListProjectsAction extends Action {

	@Override
	public ActionResult doOperation() {
		try {
			PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");
			ProjectRole pr = new ProjectRole();
			List<Persistable> projectRoles = persistenceManager.findAll(pr);
			List<Persistable> projectsList = new ArrayList<>();
			if (AppContext.getLoggedUser().getAdministrator()) {
				projectsList = persistenceManager.findAll(pr.getUserBean());
			} else if (!AppContext.getLoggedUser().getAdministrator()) {
				for (Persistable p : projectRoles) {
					ProjectRole projectRole = (ProjectRole) p;
					if (projectRole.getUserBean().equals(AppContext.getLoggedUser())) {
						projectsList.add(projectRole.getProjectBean());
					}
				}
			}
			ActionResult actionResult = new ActionResult("OK");
			actionResult.setReturnObject(projectsList);
			return actionResult;
		} catch (Exception e) {
			return new ActionResult("ERROR");
		}
	}

}
