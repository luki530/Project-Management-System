package pl.com.tt.projectmanagementsystem.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



public abstract class Action {
		
	abstract public ActionResult doOperation();
	
	protected Map<String,Object> dataMap = new HashMap<String,Object>();
	
	public Object getParam(String paramName) {
		return dataMap.get(paramName);
	}
	
	public void setParam(String paramName, Object paramValue) {
		dataMap.put(paramName, paramValue);
	}

	
	
}
