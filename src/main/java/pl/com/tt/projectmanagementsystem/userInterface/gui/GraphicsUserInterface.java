package pl.com.tt.projectmanagementsystem.userInterface.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.appContext.AppContext;
import pl.com.tt.projectmanagementsystem.databaseModel.User;
import pl.com.tt.projectmanagementsystem.userInterface.UserInterface;
import pl.com.tt.projectmanagementsystem.userInterface.gui.controller.DocumentDetailsController;
import pl.com.tt.projectmanagementsystem.userInterface.gui.controller.HomePageController;
import pl.com.tt.projectmanagementsystem.userInterface.gui.controller.LoginPageController;
import pl.com.tt.projectmanagementsystem.userInterface.gui.controller.ProjectDetailsController;

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
		if (AppContext.getLoggedUser() != null)
			AppContext.getLoggedUser().refreshPermissions();
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
		case "projectDetails":
			ProjectDetailsController projectDetailsController = new ProjectDetailsController(this);
			URL projectDetailsUrl = getClass().getClassLoader().getResource("fxml/ProjectDetails.fxml");
			setScene(projectDetailsController, projectDetailsUrl);
			break;
		case "documentDetails":
			DocumentDetailsController documentDetailsController = new DocumentDetailsController(this);
			URL documentDetailsUrl = getClass().getClassLoader().getResource("fxml/DocumentDetails.fxml");
			setScene(documentDetailsController, documentDetailsUrl);
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

	@Override
	public void showError(String prompt) {
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		Button button = new Button("OK");
		button.setOnAction(new EventHandler() {
			@Override
			public void handle(Event e) {
				dialogStage.close();
			}
		});
		VBox vbox = new VBox(new Text(prompt), button);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(15));

		dialogStage.setScene(new Scene(vbox));
		dialogStage.show();

	}
}
