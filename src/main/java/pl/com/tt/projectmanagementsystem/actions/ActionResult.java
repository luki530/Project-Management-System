package pl.com.tt.projectmanagementsystem.actions;

public class ActionResult {
	
	private String actionStatus;
	private String errorMessage;
	private Object returnObject;
	
	public ActionResult(String actionStatus){
		this.actionStatus=actionStatus;
	}

	public String getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}
	
	
	
	

}
