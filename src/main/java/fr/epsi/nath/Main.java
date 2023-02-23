package fr.epsi.nath;

import fr.epsi.b32223.bo.Livre;
import fr.epsi.nath.bo.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	public static void main( String[] args ) {

		demoCreation();
	}


	private static void demoCreation() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory( "petstore" );
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Product croquette = new Product("055639", "croquette", ProdType.FOOD, 9.00);
		Product clochette = new Product("806690", "clochette", ProdType.ACCESSORY, 4.50);
		Product shampoingChien = new Product("789006", "Shampoing pour chien", ProdType.CLEANING, 5.50);

		Address address1 = new Address("67", "rue de la Forêt", "44880", "Sautron");
		Address address2 = new Address("60", "Avene de la Paix", "44300", "Donges");
		Address address3 = new Address("14", "rue de la Crête", "44800", "Saint-Herblain");

		PetStore petStore1 = new PetStore("PetStore1", "Nathanaël Ka 1", address1);
		PetStore petStore2 = new PetStore("PetStore2", "Nathanaël Ka 2", address2);
		PetStore petStore3 = new PetStore("PetStore3", "Nathanaël Ka 3", address3);




		petStore1.addProduct(croquette);
		petStore1.addProduct(clochette);
		petStore1.addProduct(shampoingChien);

		petStore2.addProduct(croquette);
		petStore2.addProduct(clochette);
		petStore2.addProduct(shampoingChien);

		petStore3.addProduct(croquette);
		petStore3.addProduct(clochette);
		petStore3.addProduct(shampoingChien);

		Animal animal1 = new Animal(new Date(), "rouge", petStore1);
		Cat cat1 = new Cat(new Date(), "rouge", petStore1, "chipid");
		Fish fish1 = new Fish(new Date(), "rouge", petStore1, FishLivEnv.SEA_WATER);

		Animal animal2 = new Animal(new Date(), "bleu", petStore2);
		Cat cat2 = new Cat(new Date(), "bleu", petStore2, "chipid");
		Fish fish2 = new Fish(new Date(), "bleu", petStore2, FishLivEnv.SEA_WATER);

		Animal animal3 = new Animal(new Date(), "vert", petStore3);
		Cat cat3 = new Cat(new Date(), "vert", petStore3, "chipid");
		Fish fish3 = new Fish(new Date(), "vert", petStore3, FishLivEnv.FRESH_WATER);


		em.persist(croquette);
		em.persist(clochette);
		em.persist(shampoingChien);

		em.persist(petStore1);
		em.persist(petStore2);
		em.persist(petStore3);

		em.persist(animal1);
		em.persist(cat1);
		em.persist(fish1);
		em.persist(animal2);
		em.persist(cat2);
		em.persist(fish2);
		em.persist(animal3);
		em.persist(cat3);
		em.persist(fish3);


		em.getTransaction().commit();

		Query query = em.createQuery("select id, birth, couleur from Animal where petStore.id = 1");
		List resultList = query.getResultList();
		System.out.println("list animals size = " + resultList.size());
		for (Object animal : resultList) {
			System.out.println(animal);
		}

		em.close();
		emf.close();
	}
}