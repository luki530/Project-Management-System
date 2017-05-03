package pl.com.tt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pl.com.tt.databaseModel.User;

public class Startup {

	public static void main(String[] args) {
		User user = new User();
		user.setLogin("login2");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setPassword("passwd");
		user.setE_mail("mail@mail.com");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectmanagementsystem");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();

		em.close();
		emf.close();

	}

}
