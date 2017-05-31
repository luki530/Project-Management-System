package pl.com.tt.projectmanagementsystem.actions.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.appContext.AppContext;
import pl.com.tt.projectmanagementsystem.databaseModel.Document;
import pl.com.tt.projectmanagementsystem.databaseModel.ProjectRole;
import pl.com.tt.projectmanagementsystem.databaseModel.Role;
import pl.com.tt.projectmanagementsystem.databaseModel.User;
import pl.com.tt.projectmanagementsystem.persistence.Persistable;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;

public class ProjectDetailsAction extends Action {

	@Override
	public ActionResult doOperation() {
		try {
			PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");
			Map<String, Object> returnMap = new HashMap<>();
			List<Persistable> projectRolesList = persistenceManager.findAll(new ProjectRole());
			List<Persistable> currentProjectRoles = new ArrayList<>();
			for (Persistable pr : projectRolesList) {
				ProjectRole projectRole = (ProjectRole) pr;
				if (projectRole.getProjectBean().equals(AppContext.getCurrentProject())) {
					currentProjectRoles.add(projectRole);
				}
			}
			returnMap.put("currentProjectRoles", currentProjectRoles);
			List<Persistable> users = persistenceManager.findAll(new User());
			List<Persistable> roles = persistenceManager.findAll(new Role());
			returnMap.put("users", users);
			returnMap.put("roles", roles);
			List<Persistable> projectDocuments = new ArrayList<>();
			List<Persistable> documents = persistenceManager.findAll(new Document());
			for (Persistable d : documents) {
				Document document = (Document) d;
				if (document.getProjectBean().equals(AppContext.getCurrentProject())) {
					projectDocuments.add(document);
				}
			}
			returnMap.put("documents", projectDocuments);
			ActionResult ar = new ActionResult("OK");
			ar.setReturnObject(returnMap);
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			return new ActionResult("ERROR");
		}

	}

}
