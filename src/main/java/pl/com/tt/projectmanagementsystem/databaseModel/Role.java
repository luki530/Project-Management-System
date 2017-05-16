package pl.com.tt.projectmanagementsystem.databaseModel;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String role;

	//bi-directional many-to-one association to ProjectRole
	@OneToMany(mappedBy="roleBean")
	private List<ProjectRole> projectRoles;

	public Role() {
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

	public List<ProjectRole> getProjectRoles() {
		return this.projectRoles;
	}

	public void setProjectRoles(List<ProjectRole> projectRoles) {
		this.projectRoles = projectRoles;
	}

	public ProjectRole addProjectRole(ProjectRole projectRole) {
		getProjectRoles().add(projectRole);
		projectRole.setRoleBean(this);

		return projectRole;
	}

	public ProjectRole removeProjectRole(ProjectRole projectRole) {
		getProjectRoles().remove(projectRole);
		projectRole.setRoleBean(null);

		return projectRole;
	}

}