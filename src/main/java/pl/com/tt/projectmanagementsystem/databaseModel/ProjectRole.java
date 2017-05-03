package pl.com.tt.projectmanagementsystem.databaseModel;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the project_roles database table.
 * 
 */
@Entity
@Table(name="project_roles")
@NamedQuery(name="ProjectRole.findAll", query="SELECT p FROM ProjectRole p")
public class ProjectRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Lob
	private String role;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user")
	private User userBean;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="project")
	private Project projectBean;

	public ProjectRole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

	public Project getProjectBean() {
		return this.projectBean;
	}

	public void setProjectBean(Project projectBean) {
		this.projectBean = projectBean;
	}

}