package pl.com.tt.projectmanagementsystem;

import java.util.List;

import pl.com.tt.projectmanagementsystem.xmlUtil.Users.User;;

public class Test {

	public static void main(String[] args) {
	
		User u = new User();
		
		u.setName("ADMINISTRATOR");
		List<String> permissions = u.getPermissions();
		
		for(String s: permissions){
			System.out.println(s);
		}
		
	}
	
}
