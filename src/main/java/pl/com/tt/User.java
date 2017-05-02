package pl.com.tt;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String login;

	@Lob
	@Column(name="`e-mail`")
	private String e_mail;

	@Lob
	@Column(name="first_name")
	private String firstName;

	@Lob
	@Column(name="last_name")
	private String lastName;

	@Lob
	private String password;

	//bi-directional many-to-one association to Administrator
	@OneToMany(mappedBy="userBean")
	private List<Administrator> administrators;

	//bi-directional many-to-one association to Document
	@OneToMany(mappedBy="user")
	private List<Document> documents;

	//bi-directional many-to-one association to Engineer
	@OneToMany(mappedBy="userBean")
	private List<Engineer> engineers;

	//bi-directional many-to-one association to Hr
	@OneToMany(mappedBy="userBean")
	private List<Hr> hrs;

	//bi-directional many-to-one association to Manager
	@OneToMany(mappedBy="userBean")
	private List<Manager> managers;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="user")
	private List<Project> projects;

	public User() {
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public List<Administrator> getAdministrators() {
		return this.administrators;
	}

	public void setAdministrators(List<Administrator> administrators) {
		this.administrators = administrators;
	}

	public Administrator addAdministrator(Administrator administrator) {
		getAdministrators().add(administrator);
		administrator.setUserBean(this);

		return administrator;
	}

	public Administrator removeAdministrator(Administrator administrator) {
		getAdministrators().remove(administrator);
		administrator.setUserBean(null);

		return administrator;
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

	public List<Engineer> getEngineers() {
		return this.engineers;
	}

	public void setEngineers(List<Engineer> engineers) {
		this.engineers = engineers;
	}

	public Engineer addEngineer(Engineer engineer) {
		getEngineers().add(engineer);
		engineer.setUserBean(this);

		return engineer;
	}

	public Engineer removeEngineer(Engineer engineer) {
		getEngineers().remove(engineer);
		engineer.setUserBean(null);

		return engineer;
	}

	public List<Hr> getHrs() {
		return this.hrs;
	}

	public void setHrs(List<Hr> hrs) {
		this.hrs = hrs;
	}

	public Hr addHr(Hr hr) {
		getHrs().add(hr);
		hr.setUserBean(this);

		return hr;
	}

	public Hr removeHr(Hr hr) {
		getHrs().remove(hr);
		hr.setUserBean(null);

		return hr;
	}

	public List<Manager> getManagers() {
		return this.managers;
	}

	public void setManagers(List<Manager> managers) {
		this.managers = managers;
	}

	public Manager addManager(Manager manager) {
		getManagers().add(manager);
		manager.setUserBean(this);

		return manager;
	}

	public Manager removeManager(Manager manager) {
		getManagers().remove(manager);
		manager.setUserBean(null);

		return manager;
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

}