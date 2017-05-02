package pl.com.tt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pl.com.tt.databaseModel.User;

public class Startup {

	public static void main(String[] args) {
		User user = new User();
		user.setLogin("luki530");
		user.setFirstName("£ukasz");
		user.setLastName("Oszczypa³a");
		user.setPassword("testpassword");
		user.setE_mail("lukasz.oszczypala@gmail.com");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projectmanagementsystem");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();

		em.close();
		emf.close();

	}

}
