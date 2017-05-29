package pl.com.tt.projectmanagementsystem.userInterface.gui.controller;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.actions.implementations.ListProjectsAction;
import pl.com.tt.projectmanagementsystem.databaseModel.Project;
import pl.com.tt.projectmanagementsystem.userInterface.gui.GraphicsUserInterface;

public class HomePageController implements Initializable {

	private GraphicsUserInterface gui;
	private ActionResult result;
	private ObservableList projectsList = FXCollections.observableArrayList();
	
	
	@FXML
	private TextField filterField;
	@FXML
	private TableView<Project> projectTable;
	@FXML
	private TableColumn<Project, String> projectTitle;
	@FXML
	private TableColumn<Project, String> projectCreator;

	@FXML
	Button newProject;

	@FXML
	public void projectClicked() {
	}

	@FXML
	public void newProject() {
	}

	@FXML
	public void sort() {
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
		List<Project> returnedProjectsList = (List<Project>) result.getReturnObject();
		if (returnedProjectsList != null && !returnedProjectsList.isEmpty()) {
			projectsList = FXCollections.observableArrayList(returnedProjectsList);
		}
		// 0. Initialize the columns.
				projectTitle.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTitle()));
				projectCreator.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getUser().getId().toString()));

				// 1. Wrap the ObservableList in a FilteredList (initially display all
				// data).
				FilteredList<Project> filteredData = new FilteredList<>(projectsList, p -> true);

				// 2. Set the filter Predicate whenever the filter changes.
				filterField.textProperty().addListener((observable, oldValue, newValue) -> {
					filteredData.setPredicate(project -> {
						// If filter text is empty, display all persons.
						if (newValue == null || newValue.isEmpty()) {
							return true;
						}

						// Compare first name and last name of every person with filter
						// text.
						String lowerCaseFilter = newValue.toLowerCase();

						if (project.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
							return true; // Filter matches first name.
						} else if (project.getUser().getId().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
							return true; // Filter matches last name.
						}
						return false; // Does not match.
					});
				});

				// 3. Wrap the FilteredList in a SortedList.
				SortedList<Project> sortedData = new SortedList<>(filteredData);

				// 4. Bind the SortedList comparator to the TableView comparator.
				// Otherwise, sorting the TableView would have no effect.
				sortedData.comparatorProperty().bind(projectTable.comparatorProperty());

				// 5. Add sorted (and filtered) data to the table.
				projectTable.setItems(sortedData);
	}

}
