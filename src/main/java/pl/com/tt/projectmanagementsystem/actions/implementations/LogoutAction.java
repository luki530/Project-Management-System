package pl.com.tt.projectmanagementsystem.actions.implementations;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.appContext.AppContext;

public class LogoutAction extends Action {

	@Override
	public ActionResult doOperation() {
		try {
			AppContext.setLoggedUser(null);
			AppContext.setCurrentDocument(null);
			AppContext.setCurrentProject(null);
			return new ActionResult("OK");
		} catch (Exception e) {
			e.printStackTrace();
			return new ActionResult("ERROR");
		}

	}

}
