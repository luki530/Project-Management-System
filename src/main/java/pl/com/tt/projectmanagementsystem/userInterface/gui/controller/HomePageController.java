package pl.com.tt.projectmanagementsystem.userInterface.gui.controller;

import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.actions.implementations.CreateProjectAction;
import pl.com.tt.projectmanagementsystem.actions.implementations.CreateUserAction;
import pl.com.tt.projectmanagementsystem.actions.implementations.ListProjectsAction;
import pl.com.tt.projectmanagementsystem.actions.implementations.LogoutAction;
import pl.com.tt.projectmanagementsystem.appContext.AppContext;
import pl.com.tt.projectmanagementsystem.databaseModel.Project;
import pl.com.tt.projectmanagementsystem.databaseModel.User;
import pl.com.tt.projectmanagementsystem.userInterface.gui.GraphicsUserInterface;

public class HomePageController implements Initializable {

	private GraphicsUserInterface gui;
	private ActionResult result;
	private ObservableList projectsList = FXCollections.observableArrayList();
	Project temp;
	Date lastClickTime;

	@FXML
	private HBox sortBox = new HBox();
	@FXML
	private TextField filterField;
	@FXML
	private TableView<Project> projectTable;
	@FXML
	private TableColumn<Project, String> projectTitle = new TableColumn<>();
	@FXML
	private TableColumn<Project, String> projectCreator = new TableColumn<>();
	@FXML
	private TableColumn<Project, String> projectDescription = new TableColumn<>();
	@FXML
	private Button createUser;
	@FXML
	private Button createProject;
	@FXML
	private TextArea newProjectDescription;
	@FXML
	private TextField newProjectTitle;
	@FXML
	private TextField login;
	@FXML
	private CheckBox administrator;
	@FXML
	private TextField password;

	@FXML
	private TextField firstName;

	@FXML
	private TextField lastName;

	@FXML
	private TextField email;

	@FXML
	public void projectClicked() {
	}
	@FXML
	public void createUser(){
		CreateUserAction createUserAction = new CreateUserAction();
		createUserAction.setParameter("login", login.getText());
		createUserAction.setParameter("password", password.getText());
		createUserAction.setParameter("firstName", firstName.getText());
		createUserAction.setParameter("lastName", lastName.getText());
		createUserAction.setParameter("email", email.getText());
		createUserAction.setParameter("administrator", administrator.isSelected());
		
		gui.addActionToQueue(createUserAction);
		result = gui.getActionResultFromQueue();
		while (result == null) {
			result = gui.getActionResultFromQueue();
		}
		if(result.getActionStatus().equals("OK")){
			//show info
			createUser.setText("");
			login.setText("");
			password.setText("");
			firstName.setText("");
			lastName.setText("");
			email.setText("");
			administrator.setSelected(false);
			System.out.println("uzytkownik dodany");
		}

	}
	
	@FXML
	private void handleRowSelect(){
	    Project row = projectTable.getSelectionModel().getSelectedItem();
	    if (row==null) return;
	    if(row!=temp){
	        temp=row;
	        lastClickTime=new Date();
	    } else if(row==temp) {
	        Date now = new Date();
	        long diff = now.getTime() - lastClickTime.getTime();
	        if (diff < 300){
	             AppContext.setCurrentProject(projectTable.getSelectionModel().getSelectedItem());
	             gui.changeSceneTo("projectDetails");
	        } else {
	            lastClickTime = new Date();
	        }
	    }
	}
	
	@FXML
	public void createProject() {
		CreateProjectAction createProjectAction = new CreateProjectAction();
		createProjectAction.setParameter("projectTitle", newProjectTitle.getText());
		createProjectAction.setParameter("projectDescription", newProjectDescription.getText());
		gui.addActionToQueue(createProjectAction);
		result = gui.getActionResultFromQueue();
		while (result == null) {
			result = gui.getActionResultFromQueue();
		}
		if(result.getActionStatus().equals("OK")){
			gui.changeSceneTo("homePage");
		}

	}

	@FXML
	public void logout() {
		LogoutAction logoutAction = new LogoutAction();
		gui.addActionToQueue(logoutAction);
		result = gui.getActionResultFromQueue();
		while (result == null) {
			result = gui.getActionResultFromQueue();
		}
		if (result.getActionStatus().equals("OK"))
			gui.changeSceneTo("loginPage");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ListProjectsAction action = new ListProjectsAction();
		gui.addActionToQueue(action);
		result = gui.getActionResultFromQueue();
		while (result == null) {
			result = gui.getActionResultFromQueue();
		}
		showResults(result);

	}

	public HomePageController(GraphicsUserInterface gui) {
		this.gui = gui;
	}

	private void showResults(ActionResult result) {
		User loggedUser = AppContext.getLoggedUser();
		List<String> loggedUserPermissions = loggedUser.getPermissions();

		sortBox.setVisible(loggedUserPermissions.contains("showProjectsForCriteria"));
		createProject.setVisible(loggedUserPermissions.contains("createProject"));
		newProjectTitle.setVisible(loggedUserPermissions.contains("createProject"));
		newProjectDescription.setVisible(loggedUserPermissions.contains("createProject"));
		createUser.setVisible(loggedUserPermissions.contains("createNewUser"));
		login.setVisible(loggedUserPermissions.contains("createNewUser"));
		password.setVisible(loggedUserPermissions.contains("createNewUser"));
		firstName.setVisible(loggedUserPermissions.contains("createNewUser"));
		lastName.setVisible(loggedUserPermissions.contains("createNewUser"));
		email.setVisible(loggedUserPermissions.contains("createNewUser"));
		administrator.setVisible(loggedUserPermissions.contains("createNewUser"));
		List<Project> returnedProjectsList = (List<Project>) result.getReturnObject();
		if (returnedProjectsList != null && !returnedProjectsList.isEmpty()) {
			projectsList = FXCollections.observableArrayList(returnedProjectsList);
		}
		projectTitle.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTitle()));
		projectCreator.setCellValueFactory(
				cellData -> new ReadOnlyStringWrapper(cellData.getValue().getUser().getId().toString()));
		projectDescription
				.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDescription()));
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
				} else if (project.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1){
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
