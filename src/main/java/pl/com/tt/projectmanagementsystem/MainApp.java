package pl.com.tt.projectmanagementsystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.com.tt.projectmanagementsystem.databaseModel.User;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        User user = new User();
        user.setLogin("login3");
        user.setFirstName("fname");
        user.setLastName("lname");
        user.setPassword("passwd");
        user.setE_mail("mail@mail.com");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectmanagementsystem");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        em.close();
        emf.close();
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
