package pl.com.tt.projectmanagementsystem.databaseModel;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import pl.com.tt.projectmanagementsystem.appContext.AppContext;
import pl.com.tt.projectmanagementsystem.persistence.Persistable;
import pl.com.tt.projectmanagementsystem.xmlUtil.Role;
import pl.com.tt.projectmanagementsystem.xmlUtil.RolesPermissions;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable, Persistable {
	private static final long serialVersionUID = 1L;

	@Id
	private String login;
	@Column(name = "administrator")
	private boolean administrator = false;

	@Column(name = "`e-mail`")
	private String e_mail;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String password;

	@Transient
	private List<String> permissions;

	// bi-directional many-to-one association to Document
	@OneToMany(mappedBy = "user")
	private List<Document> documents;

	// bi-directional many-to-one association to ProjectRole
	@OneToMany(mappedBy = "userBean")
	private List<ProjectRole> projectRoles;

	// bi-directional many-to-one association to Project
	@OneToMany(mappedBy = "user")
	private List<Project> projects;

	public User() {
	}

	public Object getId() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<String> getPermissions() {
		this.refreshPermissions();
		return this.permissions;
	}

	public boolean getAdministrator() {
		return this.administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public String getE_mail() {
		return this.e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public Document addDocument(Document document) {
		getDocuments().add(document);
		document.setUser(this);

		return document;
	}

	public Document removeDocument(Document document) {
		getDocuments().remove(document);
		document.setUser(null);

		return document;
	}

	public List<ProjectRole> getProjectRoles() {
		return this.projectRoles;
	}

	public void setProjectRoles(List<ProjectRole> projectRoles) {
		this.projectRoles = projectRoles;
	}

	public ProjectRole addProjectRole(ProjectRole projectRole) {
		getProjectRoles().add(projectRole);
		projectRole.setUserBean(this);

		return projectRole;
	}

	public ProjectRole removeProjectRole(ProjectRole projectRole) {
		getProjectRoles().remove(projectRole);
		projectRole.setUserBean(null);

		return projectRole;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setUser(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setUser(null);

		return project;
	}

	public void refreshPermissions() {
		if (this != null) {
			List<String> userRoles = new ArrayList<>();
			List<String> userPermissions = new ArrayList<>();
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
			if (this.administrator) {
				userRoles.add("ADMINISTRATOR");
			}
			List<ProjectRole> projectRoles = this.getProjectRoles();
			for (ProjectRole pr : projectRoles) {
				if (pr.getProjectBean().equals(AppContext.getCurrentProject())) {
					userRoles.add(pr.getRoleBean().getRole());
				}
			}

			for (Role r : roles) {
				if (userRoles.contains(r.getName())) {
					for (String s : r.getActions()) {
						userPermissions.add(s);
					}
				}

			}

			if (AppContext.getCurrentDocument() != null && AppContext.getCurrentDocument().getUser().equals(this)) {
				userPermissions.add("EDIT DOCUMENT");
			}

			this.permissions = userPermissions;
		}
	}
}