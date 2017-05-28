package pl.com.tt.projectmanagementsystem.userInterface.gui;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.userInterface.UserInterface;
import pl.com.tt.projectmanagementsystem.userInterface.gui.controller.LoginPageController;

public class GraphicsUserInterface implements UserInterface {

    private static GraphicsUserInterface instance = new GraphicsUserInterface();

    private Stage stage;
    private BlockingQueue<ActionResult> actionsResults;
    private BlockingQueue<Action> actions;
	
	private GraphicsUserInterface() {
	    actionsResults = new LinkedBlockingQueue<>();
	    actions = new LinkedBlockingQueue<>();
	}
	
	public static GraphicsUserInterface getInstance() {
		return instance;
	}
	
	public void setStage(Stage stage) {
	    this.stage = stage;
	}

	public Stage getStage() {
	    return stage;
	}
	
	public void addActionResult(ActionResult actionResult) {
	    actionsResults.add(actionResult);
	}
	
    @Override
    public Map<String, Object> getParameters() {
        return null;
    }

    @Override
    public Action getActionFromQueue() {
        return actions.poll();
    }

    @Override
    public void addActionToQueue(Action action) {
        actions.add(action);
    }

    @Override
    public void addActionResultToQueue(ActionResult actionResult) {
        actionsResults.add(actionResult);
    }

    @Override
    public ActionResult getActionResultFromQueue() {
        return actionsResults.poll();
    }
    
    public void changeSceneTo(String sceneName) {
        switch (sceneName) {
        case "homePage":
            LoginPageController controller = new LoginPageController(this);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Scene.fxml"));     
            fxmlLoader.setController(controller);
            Parent root = null;
            try {
                root = (Parent)fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }          
            Scene scene = new Scene(root); 
            stage.setScene(scene);    
            stage.show();   
            break;
        case "projectsPage":
                // sadasdasd 
            break;
        default:
            break;
        }
    }
}
