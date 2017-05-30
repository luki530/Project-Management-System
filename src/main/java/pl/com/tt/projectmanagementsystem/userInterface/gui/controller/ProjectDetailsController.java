package pl.com.tt.projectmanagementsystem.userInterface.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.actions.implementations.CreateNewProjectRoleAction;
import pl.com.tt.projectmanagementsystem.actions.implementations.DeleteProjectAction;
import pl.com.tt.projectmanagementsystem.actions.implementations.LogoutAction;
import pl.com.tt.projectmanagementsystem.actions.implementations.ProjectDetailsAction;
import pl.com.tt.projectmanagementsystem.appContext.AppContext;
import pl.com.tt.projectmanagementsystem.databaseModel.Document;
import pl.com.tt.projectmanagementsystem.databaseModel.ProjectRole;
import pl.com.tt.projectmanagementsystem.databaseModel.Role;
import pl.com.tt.projectmanagementsystem.databaseModel.User;
import pl.com.tt.projectmanagementsystem.userInterface.gui.GraphicsUserInterface;

public class ProjectDetailsController implements Initializable {

	private GraphicsUserInterface gui;
	private ActionResult result;
	private ObservableList<ProjectRole> projectRolesList = FXCollections.observableArrayList();
	private ObservableList<Document> documentsList = FXCollections.observableArrayList();

	@FXML
	private TableView<ProjectRole> projectroles;

	@FXML
	private TableColumn<ProjectRole, String> roleUser;

	@FXML
	private TableColumn<ProjectRole, String> roleRole;

	@FXML
	private Button newRole;

	@FXML
	private SplitMenuButton newUserRole;

	@FXML
	private SplitMenuButton newRoleRole;

	@FXML
	private TableView<Document> documents;

	@FXML
	private TableColumn<Document, String> documentName;

	@FXML
	private TableColumn<Document, String> documentDescription;

	@FXML
	private TableColumn<Document, String> documentCreator;

	@FXML
	private TableColumn<Document, String> documentTopic;

	@FXML
	private Button logout;

	@FXML
	private Button deleteProject;

	@FXML
	private Button newDocument;

	@FXML
	private TextField newDocumentName;

	@FXML
	private TextField newDocumentTopic;

	@FXML
	private TextArea newDocumentDescription;

	User userToRole;
	Role roleToRole;

	@FXML
	void deleteProject(ActionEvent event) {
		DeleteProjectAction deleteProjectAction = new DeleteProjectAction();
		gui.addActionToQueue(deleteProjectAction);
		result = gui.getActionResultFromQueue();
		while (result == null) {
			result = gui.getActionResultFromQueue();
		}
		if (result.getActionStatus().equals("OK"))
			gui.changeSceneTo("homePage");
	}

	@FXML
	void logout(ActionEvent event) {
		LogoutAction logoutAction = new LogoutAction();
		gui.addActionToQueue(logoutAction);
		result = gui.getActionResultFromQueue();
		while (result == null) {
			result = gui.getActionResultFromQueue();
		}
		if (result.getActionStatus().equals("OK"))
			gui.changeSceneTo("loginPage");
	}

	@FXML
	void newDocument(ActionEvent event) {

	}

	@FXML
	void newRole(ActionEvent event) {
		CreateNewProjectRoleAction createNewProjectRoleAction = new CreateNewProjectRoleAction();
		createNewProjectRoleAction.setParameter("user", userToRole);
		createNewProjectRoleAction.setParameter("role", roleToRole);
		gui.addActionToQueue(createNewProjectRoleAction);
		result = gui.getActionResultFromQueue();
		while (result == null) {
			result = gui.getActionResultFromQueue();
		}
		if (result.getActionStatus().equals("OK")) {
			gui.changeSceneTo("projectDetails");
		}
	}

	public ProjectDetailsController(GraphicsUserInterface gui) {
		this.gui = gui;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		projectroles.setVisible(AppContext.getLoggedUser().getPermissions().contains("showInformationsAboutProject"));
		newRole.setVisible(AppContext.getLoggedUser().getPermissions().contains("addUserToRole"));
		newUserRole.setVisible(AppContext.getLoggedUser().getPermissions().contains("addUserToRole"));
		newRoleRole.setVisible(AppContext.getLoggedUser().getPermissions().contains("addUserToRole"));
		documents.setVisible(AppContext.getLoggedUser().getPermissions().contains("viewDocuments"));
		deleteProject.setVisible(AppContext.getLoggedUser().getPermissions().contains("deleteProject"));
		newDocument.setVisible(AppContext.getLoggedUser().getPermissions().contains("createDocument"));
		newDocumentName.setVisible(AppContext.getLoggedUser().getPermissions().contains("createDocument"));
		newDocumentTopic.setVisible(AppContext.getLoggedUser().getPermissions().contains("createDocument"));
		newDocumentDescription.setVisible(AppContext.getLoggedUser().getPermissions().contains("createDocument"));
		ProjectDetailsAction action = new ProjectDetailsAction();
		gui.addActionToQueue(action);
		result = gui.getActionResultFromQueue();
		while (result == null) {
			result = gui.getActionResultFromQueue();
		}

		showResults(result);

	}

	private void showResults(ActionResult actionResult) {
		roleUser.setCellValueFactory(
				cellData -> new ReadOnlyStringWrapper(cellData.getValue().getUserBean().getId().toString()));
		roleRole.setCellValueFactory(
				cellData -> new ReadOnlyStringWrapper(cellData.getValue().getRoleBean().getRole()));

		Map<String, Object> resultMap = (Map) actionResult.getReturnObject();
		if (resultMap != null) {
			List<ProjectRole> currentProjectRolesList = (List<ProjectRole>) resultMap.get("currentProjectRoles");
			if (currentProjectRolesList != null) {
				projectRolesList = FXCollections.observableArrayList(currentProjectRolesList);
				projectroles.setItems(projectRolesList);
			}
			List<User> usersList = (List<User>) resultMap.get("users");
			if (usersList != null) {

				for (User u : usersList) {
					MenuItem menuItem = new MenuItem();
					menuItem.setUserData(u);
					menuItem.setText(u.getId().toString());
					menuItem.setOnAction(new EventHandler() {
						@Override
						public void handle(Event e) {
							userToRole = (User) ((MenuItem) e.getSource()).getUserData();
							newUserRole.setText(userToRole.getId().toString());
						}
					});
					newUserRole.getItems().add(menuItem);
				}
			}

			List<Role> rolesList = (List<Role>) resultMap.get("roles");
			if (rolesList != null) {

				for (Role r : rolesList) {
					MenuItem menuItem = new MenuItem();
					menuItem.setUserData(r);
					menuItem.setText(r.getRole());
					menuItem.setOnAction(new EventHandler() {
						@Override
						public void handle(Event e) {
							roleToRole = (Role) ((MenuItem) e.getSource()).getUserData();
							newRoleRole.setText(roleToRole.getRole());
						}
					});
					newRoleRole.getItems().add(menuItem);
				}
			}
			documentCreator.setCellValueFactory(
					cellData -> new ReadOnlyStringWrapper(cellData.getValue().getUser().getId().toString()));
			documentDescription
					.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDescription()));
			documentName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTitle()));
			documentTopic.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTopic()));
			List<Document> currentDocumentsList = (List<Document>) resultMap.get("documents");
			if (currentDocumentsList != null) {
				documentsList = FXCollections.observableArrayList(currentDocumentsList);
				documents.setItems(documentsList);
			}

		}
	}
}
