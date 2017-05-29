package pl.com.tt.projectmanagementsystem.userInterface.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.userInterface.UserInterface;
import pl.com.tt.projectmanagementsystem.userInterface.gui.controller.HomePageController;
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
		case "loginPage":
			LoginPageController loginPageController = new LoginPageController(this);
			URL loginPageUrl = getClass().getClassLoader().getResource("fxml/LoginPage.fxml");
			setScene(loginPageController, loginPageUrl);
			break;
		case "homePage":
			HomePageController homePageController = new HomePageController(this);
			URL homePageUrl = getClass().getClassLoader().getResource("fxml/HomePage.fxml");
			setScene(homePageController, homePageUrl);
			break;
		default:
			break;
		}
	}

	private void setScene(Object controller, URL fxmlURL) {
		FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
		fxmlLoader.setController(controller);
		AnchorPane root = null;
		try {
			root = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
