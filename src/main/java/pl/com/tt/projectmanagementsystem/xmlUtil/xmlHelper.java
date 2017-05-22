package pl.com.tt.projectmanagementsystem.xmlUtil;


import java.io.InputStream;

import javax.xml.bind.JAXBContext;

public class xmlHelper {

	RolesPermissions rp ;
	InputStream file = ClassLoader.getSystemResourceAsStream("Permissions.xml");
	JAXBContext jaxbContext = JAXBContext.newInstance(RolesPermissions.class);
//	
//	Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//	
//	rp=(RolesPermissions)jaxbUnmarshaller.unmarshal(file);

//	public List<String> getPermissions(String role) {
//
//		return null;
//	}
//	
//	List<Role> roles = rp.getRoles();
//    for (Role r : roles) {
//        System.out.println(r.getName());
//        List<String> actions = r.getActions();
//        for (String a : actions) {
//            System.out.println("\t" + a);
//        }
//    }

}
