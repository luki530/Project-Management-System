package pl.com.tt.projectmanagementsystem.userInterface.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.actions.implementations.ListProjectsAction;
import pl.com.tt.projectmanagementsystem.userInterface.gui.GraphicsUserInterface;

public class HomePageController implements Initializable {

	private GraphicsUserInterface gui;
	private ActionResult result;
	private ObservableList projectsList;
	
	@FXML
	ListView projectList;
	@FXML
	Button newProject;
	@FXML
	Button sort;
	@FXML 
	ChoiceBox sortBy;
	
	@FXML
	public void projectClicked(){}
	@FXML
	public void newProject(){}
	@FXML
	public void sort(){}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ListProjectsAction action = new ListProjectsAction();
		gui.addActionToQueue(action);
		result = gui.getActionResultFromQueue();
		while (result == null) {
			result = gui.getActionResultFromQueue();
		}
		doSomethingWithResults(result);

	}

	public HomePageController(GraphicsUserInterface gui) {
		this.gui = gui;
	}

	private void doSomethingWithResults(ActionResult result) {	
		projectsList.addAll(result.getReturnObject());
		projectList.setItems(projectsList);
		

	}

}
