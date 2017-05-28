package pl.com.tt.projectmanagementsystem.userInterface;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;

public interface UserInterface{
	
	void showError();
	
	public void addActionResult(ActionResult actionResult); 
	
	void showMessage(String message);

	Map<String, String> getParameters(List<String> parameters);
	
	

}
