package pl.com.tt.projectmanagementsystem.actions.implementations;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.appContext.AppContext;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;

public class DeleteProjectAction extends Action {

	@Override
	public ActionResult doOperation() {
		try {
			PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");
			persistenceManager.delete(AppContext.getCurrentProject());
			AppContext.setCurrentProject(null);
			return new ActionResult("OK");
		} catch (Exception e) {
			e.printStackTrace();
			return new ActionResult("OK");
		}
		
	}

}
