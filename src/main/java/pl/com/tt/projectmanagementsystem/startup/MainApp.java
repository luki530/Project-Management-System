package pl.com.tt.projectmanagementsystem.startup;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.com.tt.projectmanagementsystem.databaseModel.User;
import pl.com.tt.projectmanagementsystem.persistence.PersistenceManager;
import pl.com.tt.projectmanagementsystem.startup.configuration.MainConfiguration;
import pl.com.tt.projectmanagementsystem.startup.configuration.ProjectManagementSystem;

public class MainApp extends Application {
	

	@Override
	public void start(Stage stage) throws Exception {
		
		MainConfiguration config = MainConfiguration.createDevelopmentConfig();
		ProjectManagementSystem pms = new ProjectManagementSystem(config);
		Thread thread = new Thread(pms);
		thread.start();
		
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight()*0.91);
		scene.getStylesheets().add("/styles/Styles.css");
		stage.setTitle("JavaFX and Maven");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
		
		
		
		// User user = new User();
		// user.setLogin("login9");
		// user.setFirstName("fname1");
		// user.setLastName("lname1");
		// user.setPassword("passwd1");
		// user.setE_mail("mail@mai1l.com");
		// user.setAdministrator(true);
		// EntityManagerFactory emf =
		// Persistence.createEntityManagerFactory("projectmanagementsystem");
		// EntityManager em = emf.createEntityManager();
		//
		// em.getTransaction().begin();
		// em.persist(user);
		// em.getTransaction().commit();
		//
		// em.close();
		// emf.close();

		// List<String> permissions = new ArrayList<>();
		// permissions.add("ADMINISTRATOR");
		// permissions.add("MANAGER");
		// permissions = XmlHelper.getPermissions(permissions);
		// for(String s: permissions){
		// System.out.println(s);
		// }
		// XmlWatcher.start();

		
		
		
		
//		PersistenceManager mp = pms.getPersistenceManager();
//		User user = new User();
//		/*
//		 * Brzydkie rzutowanie, ale skoro sami przekazujemy do metody obiekt
//		 * User, to mamy pewność, że zwróci nam listę obiektów typu User
//		 */
//		List<User> foundUsers = (List<User>) (List<?>) mp.findAll(user);
//		System.out.println(foundUsers.toString());
		/*
		 * Jak wyżej...
		 */
		// User foundUser = (User) mp.find(user, 1);
	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX application.
	 * main() serves only as fallback in case the application can not be
	 * launched through deployment artifacts, e.g., in IDEs with limited FX
	 * support. NetBeans ignores main().
	 *
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
