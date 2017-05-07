package pl.com.tt.projectmanagementsystem.databaseModel;

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

	private String description;

	private String title;

	//bi-directional many-to-one association to Document
	@OneToMany(mappedBy="projectBean")
	private List<Document> documents;

	//bi-directional many-to-one association to ProjectRole
	@OneToMany(mappedBy="projectBean")
	private List<ProjectRole> projectRoles;

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

	public List<Document> getDocuments() {
		return this.documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public Document addDocument(Document document) {
		getDocuments().add(document);
		document.setProjectBean(this);

		return document;
	}

	public Document removeDocument(Document document) {
		getDocuments().remove(document);
		document.setProjectBean(null);

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
		projectRole.setProjectBean(this);

		return projectRole;
	}

	public ProjectRole removeProjectRole(ProjectRole projectRole) {
		getProjectRoles().remove(projectRole);
		projectRole.setProjectBean(null);

		return projectRole;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}