package pl.com.tt.projectmanagementsystem;

import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import pl.com.tt.projectmanagementsystem.xmlUtil.Role;
import pl.com.tt.projectmanagementsystem.xmlUtil.RolesPermissions;

public class Test {

    public static void main(String[] args) {

        RolesPermissions rp = null;
        InputStream file = ClassLoader.getSystemResourceAsStream("Permissions.xml");
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RolesPermissions.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            rp = (RolesPermissions) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        List<Role> roles = rp.getRoles();
        for (Role r : roles) {
            System.out.println(r.getName());
            List<String> actions = r.getActions();
            for (String a : actions) {
                System.out.println("\t" + a);
            }
        }
    }
}
