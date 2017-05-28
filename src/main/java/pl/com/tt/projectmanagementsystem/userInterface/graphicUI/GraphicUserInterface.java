package pl.com.tt.projectmanagementsystem.userInterface.graphicUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.startup.MainApp;
import pl.com.tt.projectmanagementsystem.userInterface.UserInterface;

public class GraphicUserInterface implements UserInterface {

	private Queue<ActionResult> actionsResults = new LinkedList<>();

	public void addActionResult(ActionResult actionResult) {
		actionsResults.add(actionResult);
	}

	public static UserInterface getInstance() {
		return new GraphicUserInterface();
	}

	@Override
	public void showError() {
		// TODO Auto-generated method stub

	}

	@Override
	public void showMessage(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, String> getParameters(List<String> parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
