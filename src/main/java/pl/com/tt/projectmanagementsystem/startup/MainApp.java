package pl.com.tt.projectmanagementsystem.startup;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.com.tt.projectmanagementsystem.databaseModel.User;
import pl.com.tt.projectmanagementsystem.xmlUtil.XmlHelper;
import pl.com.tt.projectmanagementsystem.xmlUtil.XmlWatcher;

public class MainApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
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

		// Parent root =
		// FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
		//
		// Scene scene = new Scene(root);
		// scene.getStylesheets().add("/styles/Styles.css");
		//
		// stage.setTitle("JavaFX and Maven");
		// stage.setScene(scene);
		// stage.show();
		//
		// List<String> permissions = new ArrayList<>();
		// permissions.add("ADMINISTRATOR");
		// permissions.add("MANAGER");
		// permissions = XmlHelper.getPermissions(permissions);
		// for(String s: permissions){
		// System.out.println(s);
		// }

		XmlWatcher.start();

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
