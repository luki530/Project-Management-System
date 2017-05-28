package pl.com.tt.projectmanagementsystem.actions.implementations;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.databaseModel.User;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;

public class Login extends Action {

	@Override
	public ActionResult doOperation() {
		PersistenceManager persistenceManager = (PersistenceManager) getParam("persistenceManager");
		String login = (String) getParam("login");
		User user = new User();
		user = (User) persistenceManager.find(user, login);
		String password = (String) getParam("password");
		if(user.getPassword().equals(password)) return new ActionResult("OK");
		else return new ActionResult("ERROR");
	}
}
