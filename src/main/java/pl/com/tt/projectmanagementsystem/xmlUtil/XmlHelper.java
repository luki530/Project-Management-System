package pl.com.tt.projectmanagementsystem.xmlUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlHelper {

    private static final String RESOURCE_NAME = "Permissions.xml";
    private InputStream resourceInputStream;
    
    public List<String> getPermissions(String role) {
        List<String> permissions = new ArrayList<>();
        RolesPermissions rolesPermissions = getRolesPermissionsFromResource();
        //TODO: rest of method logic
        return permissions;
    }

    private RolesPermissions getRolesPermissionsFromResource() {
        RolesPermissions rolesPermissiosn = new RolesPermissions();
        try {
            rolesPermissiosn = unmarshallRolessPermissions();
        } catch (JAXBException e) {
            //TODO: handle exception
        }
        return rolesPermissiosn;
    }
    
    private RolesPermissions unmarshallRolessPermissions() throws JAXBException {
        Unmarshaller jaxbUnmarshaller = createJaxbUnmarshaller();
        return (RolesPermissions) jaxbUnmarshaller.unmarshal(resourceInputStream);
    }
    
    private Unmarshaller createJaxbUnmarshaller() throws JAXBException {
        JAXBContext jaxbContext = createJAXBContext();
        return jaxbContext.createUnmarshaller();
    }
    
    private JAXBContext createJAXBContext() throws JAXBException {
        resourceInputStream = createResourceInputStream();
        return JAXBContext.newInstance(RolesPermissions.class);
    }

    private InputStream createResourceInputStream() {
        return ClassLoader.getSystemResourceAsStream(RESOURCE_NAME);
    }

}
