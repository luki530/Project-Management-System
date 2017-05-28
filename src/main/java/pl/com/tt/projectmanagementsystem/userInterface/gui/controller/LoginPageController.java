package pl.com.tt.projectmanagementsystem.userInterface.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;
import pl.com.tt.projectmanagementsystem.actions.implementations.LoginAction;
import pl.com.tt.projectmanagementsystem.userInterface.gui.GraphicsUserInterface;

public class LoginPageController implements Initializable {
    
    private GraphicsUserInterface gui;
    private ActionResult result;
    
    @FXML
    private TextField login;
    @FXML
    private TextField password;
    
    public LoginPageController(GraphicsUserInterface gui) {
        this.gui = gui;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    private void submit() {
        Action loginAction = new LoginAction();
        loginAction.setParameter("login", login.getText());
        loginAction.setParameter("password", password.getText());
//        Task task = new Task<Void>() {
//            @Override public Void call() {
            	
                    gui.addActionToQueue(loginAction);
                    result = gui.getActionResultFromQueue();
                    while (result == null) {
                        result = gui.getActionResultFromQueue();
                    }
                    System.out.println("login");
                    doSomethingWithResults(result);
//                return null;
//            }
//        };
        
    }
    
    private void doSomethingWithResults(ActionResult result) {
        result.getActionStatus(); // Jak ERROR to wyświetlić informację
        gui.changeSceneTo("homePage");
    };
}
