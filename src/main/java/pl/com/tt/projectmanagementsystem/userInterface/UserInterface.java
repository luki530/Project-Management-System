package pl.com.tt.projectmanagementsystem.userInterface;

import java.util.List;
import java.util.Map;

import pl.com.tt.projectmanagementsystem.actions.Action;
import pl.com.tt.projectmanagementsystem.actions.ActionResult;

public interface UserInterface{
	
	void addActionResultToQueue(ActionResult actionResult); 
	
	ActionResult getActionResultFromQueue();
	
	Map<String, Object> getParameters();
	
	Action getActionFromQueue();
	
	void addActionToQueue(Action action);
}
