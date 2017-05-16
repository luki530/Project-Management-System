package pl.com.tt.projectmanagementsystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import pl.com.tt.projectmanagementsystem.xmlUtil.Users;
import pl.com.tt.projectmanagementsystem.xmlUtil.Users.User;;

public class Test {

	public static void main(String[] args) {

		Users u = new Users();

		File file = new File(
				"C:\\Users\\lukas\\Desktop\\java_workspace\\ProjectManagementSystem\\src\\main\\java\\pl\\com\\tt\\projectmanagementsystem\\xmlUtil\\Permissions.xml");

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

			u = (Users) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<User> users = u.getUser();
		List<String> permissions=null;
		String userType = "ADMINISTRATOR";

		for (User u1 : users) {
			if (u1.getName() == userType) {
				permissions = u1.getPermissions();
			}

		}
		
		for(String s: permissions){
			System.out.println(s);
		}
		
		

	}

}
