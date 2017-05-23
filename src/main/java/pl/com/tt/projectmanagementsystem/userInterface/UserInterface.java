package pl.com.tt.projectmanagementsystem.userInterface;

import java.util.List;
import java.util.Map;

public interface UserInterface {
	
	void showError();
	
	void showMessage(String message);

	Map<String, String> getParameters(List<String> parameters);

}
