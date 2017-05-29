package pl.com.tt.projectmanagementsystem.actions.implementations;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.appContext.AppContext;
import pl.com.tt.projectmanagementsystem.databaseModel.User;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;

public class LoginAction extends Action {

	@Override
	public ActionResult doOperation() {
		PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");
		String login = (String) getParameter("login");
		User user = new User();
		user = (User) persistenceManager.find(user, login);
		if(user!=null){
			String password = (String) getParameter("password");
			if(user.getPassword().equals(password)){
				AppContext.setLoggedUser(user);
				return new ActionResult("OK");
			}
		}
		return new ActionResult("ERROR");
	}
}
