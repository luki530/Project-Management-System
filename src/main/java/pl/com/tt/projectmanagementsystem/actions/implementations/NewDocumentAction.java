package pl.com.tt.projectmanagementsystem.actions.implementations;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.appContext.AppContext;
import pl.com.tt.projectmanagementsystem.databaseModel.Document;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;

public class NewDocumentAction extends Action {

	@Override
	public ActionResult doOperation() {
		try {
			PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");
			Document document = new Document();
			document.setDescription((String) getParameter("description"));
			document.setProjectBean(AppContext.getCurrentProject());
			document.setUser(AppContext.getLoggedUser());
			document.setTitle((String) getParameter("title"));
			document.setTopic((String) getParameter("topic"));
			persistenceManager.create(document);
			return new ActionResult("OK");
		} catch (Exception e) {
			e.printStackTrace();
			return new ActionResult("ERROR");
		}
	}

}
