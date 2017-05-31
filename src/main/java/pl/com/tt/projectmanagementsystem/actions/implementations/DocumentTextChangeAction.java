package pl.com.tt.projectmanagementsystem.actions.implementations;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.appContext.AppContext;
import pl.com.tt.projectmanagementsystem.databaseModel.Document;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;

public class DocumentTextChangeAction extends Action {

	@Override
	public ActionResult doOperation() {
		try {
			PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");
			Document updateDocument = AppContext.getCurrentDocument();
			updateDocument.setText((String)getParameter("newText"));
			AppContext.setCurrentDocument(updateDocument);
			persistenceManager.update(AppContext.getCurrentDocument());
			return new ActionResult("OK");
		} catch (Exception e) {
			e.printStackTrace();
			return new ActionResult("ERROR");
		}
		
		
	}

}
