package pl.com.tt.projectmanagementsystem.userInterface.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.actions.implementations.LogoutAction;
import pl.com.tt.projectmanagementsystem.appContext.AppContext;
import pl.com.tt.projectmanagementsystem.userInterface.gui.GraphicsUserInterface;

public class DocumentDetailsController implements Initializable {
	@FXML
	private Button logout;

	@FXML
	private Button deleteDocument;

	@FXML
	private Button save;

	@FXML
	private TextArea text;

	private GraphicsUserInterface gui;
	private ActionResult result;
	
	public DocumentDetailsController(GraphicsUserInterface gui) {
		this.gui = gui;
	}

	@FXML
	void deleteDocument() {

	}

	@FXML
	void logout() {
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
	void save() {
		

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		text.setVisible(AppContext.getLoggedUser().getPermissions().contains("viewDocuments"));
		text.setEditable(AppContext.getLoggedUser().getPermissions().contains("EDIT DOCUMENT"));
		save.setVisible(AppContext.getLoggedUser().getPermissions().contains("EDIT DOCUMENT"));
		deleteDocument.setVisible(AppContext.getLoggedUser().getPermissions().contains("deleteDocument"));
		text.setText(AppContext.getCurrentDocument().getText());

	}

}
