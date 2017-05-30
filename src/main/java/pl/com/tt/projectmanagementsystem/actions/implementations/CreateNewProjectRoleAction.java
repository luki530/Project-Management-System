package pl.com.tt.projectmanagementsystem.actions.implementations;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.appContext.AppContext;
import pl.com.tt.projectmanagementsystem.databaseModel.Project;
import pl.com.tt.projectmanagementsystem.databaseModel.ProjectRole;
import pl.com.tt.projectmanagementsystem.databaseModel.Role;
import pl.com.tt.projectmanagementsystem.databaseModel.User;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;

public class CreateNewProjectRoleAction extends Action {

	@Override
	public ActionResult doOperation() {
		try {
			PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");
			User user = (User) getParameter("user");
			Role role = (Role) getParameter("role");
			Project project = AppContext.getCurrentProject();
			ProjectRole projectRole = new ProjectRole();
			projectRole.setProjectBean(project);
			projectRole.setRoleBean(role);
			projectRole.setUserBean(user);
			persistenceManager.create(projectRole);
			return new ActionResult("OK");
		} catch (Exception e) {
			e.printStackTrace();
			return new ActionResult("ERROR");
		}
		
	}

}
