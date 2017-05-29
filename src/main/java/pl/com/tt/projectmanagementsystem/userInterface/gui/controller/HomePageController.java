package pl.com.tt.projectmanagementsystem.userInterface.gui.controller;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.actions.implementations.ListProjectsAction;
import pl.com.tt.projectmanagementsystem.appContext.AppContext;
import pl.com.tt.projectmanagementsystem.databaseModel.Project;
import pl.com.tt.projectmanagementsystem.databaseModel.User;
import pl.com.tt.projectmanagementsystem.userInterface.gui.GraphicsUserInterface;

public class HomePageController implements Initializable {

	private GraphicsUserInterface gui;
	private ActionResult result;
	private ObservableList projectsList = FXCollections.observableArrayList();

	@FXML
	private HBox sortBox = new HBox();
	@FXML
	private TextField filterField;
	@FXML
	private TableView<Project> projectTable;
	@FXML
	private TableColumn<Project, String> projectTitle = new TableColumn<>();
	@FXML
	private TableColumn<Project, String> projectCreator = new TableColumn<Project, String>();

	@FXML
	Button newProject;

	@FXML
	public void projectClicked() {
		System.out.println("WEJDZ W PROJEKT" + projectsList.get((projectTable.getSelectionModel().getSelectedIndex())).toString());
	}

	@FXML
	public void newProject() {

	}

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
		User loggedUser = AppContext.getLoggedUser();
		List<String> loggedUserPermissions = loggedUser.getPermissions();

		sortBox.setVisible(loggedUserPermissions.contains("showProjectsForCriteria"));

		List<Project> returnedProjectsList = (List<Project>) result.getReturnObject();
		if (returnedProjectsList != null && !returnedProjectsList.isEmpty()) {
			projectsList = FXCollections.observableArrayList(returnedProjectsList);
		}
		projectTitle.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTitle()));
		projectCreator.setCellValueFactory(
				cellData -> new ReadOnlyStringWrapper(cellData.getValue().getUser().getId().toString()));

		FilteredList<Project> filteredData = new FilteredList<>(projectsList, p -> true);

		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(project -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				String lowerCaseFilter = newValue.toLowerCase();

				if (project.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				} else if (project.getUser().getId().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true;
				}
				return false;
			});
		});

		SortedList<Project> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(projectTable.comparatorProperty());
		projectTable.setItems(sortedData);
	}

}
