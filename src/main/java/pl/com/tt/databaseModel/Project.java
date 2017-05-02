package pl.com.tt.databaseModel;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the projects database table.
 * 
 */
@Entity
@Table(name="projects")
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String description;

	@Lob
	private String title;

	//bi-directional many-to-one association to Administrator
	@OneToMany(mappedBy="projectBean")
	private List<Administrator> administrators;

	//bi-directional many-to-one association to Engineer
	@OneToMany(mappedBy="projectBean")
	private List<Engineer> engineers;

	//bi-directional many-to-one association to Hr
	@OneToMany(mappedBy="projectBean")
	private List<Hr> hrs;

	//bi-directional many-to-one association to Manager
	@OneToMany(mappedBy="projectBean")
	private List<Manager> managers;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="creator")
	private User user;

	public Project() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Administrator> getAdministrators() {
		return this.administrators;
	}

	public void setAdministrators(List<Administrator> administrators) {
		this.administrators = administrators;
	}

	public Administrator addAdministrator(Administrator administrator) {
		getAdministrators().add(administrator);
		administrator.setProjectBean(this);

		return administrator;
	}

	public Administrator removeAdministrator(Administrator administrator) {
		getAdministrators().remove(administrator);
		administrator.setProjectBean(null);

		return administrator;
	}

	public List<Engineer> getEngineers() {
		return this.engineers;
	}

	public void setEngineers(List<Engineer> engineers) {
		this.engineers = engineers;
	}

	public Engineer addEngineer(Engineer engineer) {
		getEngineers().add(engineer);
		engineer.setProjectBean(this);

		return engineer;
	}

	public Engineer removeEngineer(Engineer engineer) {
		getEngineers().remove(engineer);
		engineer.setProjectBean(null);

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
		hr.setProjectBean(this);

		return hr;
	}

	public Hr removeHr(Hr hr) {
		getHrs().remove(hr);
		hr.setProjectBean(null);

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
		manager.setProjectBean(this);

		return manager;
	}

	public Manager removeManager(Manager manager) {
		getManagers().remove(manager);
		manager.setProjectBean(null);

		return manager;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}