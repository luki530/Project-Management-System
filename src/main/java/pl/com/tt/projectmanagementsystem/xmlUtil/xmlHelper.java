package pl.com.tt.projectmanagementsystem.xmlUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlHelper {

	private static final String RESOURCE_NAME = "Permissions.xml";

	public static List<String> getPermissions(List<String> userRoles) {
		List<String> permissions = new ArrayList<>();
		RolesPermissions rolesPermissions = getRolesPermissionsFromResource();
		List<Role> roles = rolesPermissions.getRoles();
		for (String s : userRoles) {
			for (Role r : roles) {
				if (r.getName().equals(s)) {
					for (String action : r.getActions()) {
						permissions.add(action);
					}
				}
			}
		}
		return permissions;
	}

	private static RolesPermissions getRolesPermissionsFromResource() {
		RolesPermissions rolesPermissiosn = new RolesPermissions();
		try {
			rolesPermissiosn = unmarshallRolessPermissions();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return rolesPermissiosn;
	}

	private static RolesPermissions unmarshallRolessPermissions() throws JAXBException {
		InputStream resourceInputStream = createResourceInputStream();
		Unmarshaller jaxbUnmarshaller = createJaxbUnmarshaller();
		return (RolesPermissions) jaxbUnmarshaller.unmarshal(resourceInputStream);
	}

	private static InputStream createResourceInputStream() {
		return ClassLoader.getSystemResourceAsStream(RESOURCE_NAME);
	}

	private static Unmarshaller createJaxbUnmarshaller() throws JAXBException {
		JAXBContext jaxbContext = createJAXBContext();
		return jaxbContext.createUnmarshaller();
	}

	private static JAXBContext createJAXBContext() throws JAXBException {
		return JAXBContext.newInstance(RolesPermissions.class);
	}

}
