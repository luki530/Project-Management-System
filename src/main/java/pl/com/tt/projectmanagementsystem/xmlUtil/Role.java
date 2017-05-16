package pl.com.tt.projectmanagementsystem.xmlUtil;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Role {

    private String name;
    
    @XmlElementWrapper(name="actions")
    @XmlElement(name = "action")
    private List<String> actions = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setAction(List<String> actions) {
        this.actions = actions;
    }
    
}
