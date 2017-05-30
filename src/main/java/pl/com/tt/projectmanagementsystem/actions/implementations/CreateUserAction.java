package pl.com.tt.projectmanagementsystem.actions.implementations;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.databaseModel.User;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;

public class CreateUserAction extends Action {

	@Override
	public ActionResult doOperation() {
		try {
			PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");
			String login = (String) getParameter("login");
			String password = (String) getParameter("password");
			String firstName = (String) getParameter("firstName");
			String lastName = (String) getParameter("lastName");
			String email = (String) getParameter("email");
			Boolean administrator = (Boolean) getParameter("administrator");
			User user = new User();
			user.setLogin(login);
			user.setPassword(password);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setE_mail(email);
			user.setAdministrator(administrator);
			persistenceManager.create(user);
			return new ActionResult("OK");
		} catch (Exception e) {
			e.printStackTrace();
			return new ActionResult("ERROR");
		}
	}

}
