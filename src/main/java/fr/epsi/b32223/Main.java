package fr.epsi.b32223;

import fr.epsi.b32223.bo.Client;
import fr.epsi.b32223.bo.Emprunt;
import fr.epsi.b32223.bo.Livre;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class Main {
	public static void main( String[] args ) {
		
		demoCreation();
	}
	private static void tp1() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory( "pu-demo" );
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//Sélection simple par id
		Livre livre = em.find( Livre.class, 3 );
		if ( livre != null ) {
			System.out.println( livre );
		}
		
		
		//Création d'un nouveau livre
		Livre livreAAjouter = new Livre( "GOT", "GRM" );
		em.persist( livreAAjouter );
		
		
		//Modification du livre 5
		Livre livreAModifier = em.find( Livre.class, 5 );
		if ( livreAModifier != null ) {
			livreAModifier.setTitre( "Du plaisir dans la cuisine" );
		}
		
		//Recherche d'un livre via son auteur by Julien
		TypedQuery<Livre> query = em.createQuery( "select l from Livre l where l.author='Emile Zola'", Livre.class );
		Livre zola = query.getSingleResult();
		System.out.println( zola );
		
		//Recherche d'un livre via son titre
		TypedQuery<Livre> queryGOT = em.createQuery( "select l from Livre l where l.titre='GOT'", Livre.class );
		Livre got = queryGOT.getResultList().get(0);
		System.out.println( got );
		
		//Suppression d'un GOT
		Livre livreASupprimer = em.find( Livre.class, 7 );
		if (null != livreASupprimer) {
			em.remove( livreASupprimer );
		}
		
		//Recherche & affichage de tous les livres
		TypedQuery<Livre> findAllQuery = em.createQuery( "from Livre", Livre.class );
		List<Livre> list = findAllQuery.getResultList();
		for ( Livre item : list ) {
			System.out.println(item);
		}
		
		
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	private static void tp2() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory( "pu-demo" );
		EntityManager em = emf.createEntityManager();
		
		Emprunt emprunt = em.find( Emprunt.class, 2 );
		System.out.println("Fin de la première requête");
		
		System.out.println("Chargement tardif de client");
		System.out.println(emprunt.getClient());
		System.out.println("chargement tardif des livres");
		for(Livre item : emprunt.getLivres()) {
			System.out.println(item);
		}
		System.out.println("Fin de la récup des livres");
		
		Client client = em.find( Client.class, 1 );
		System.out.println("Fin de la récup du client");
		System.out.println(client.getEmprunts());
		System.out.println("Fin de la récup des emprunts");
		em.close();
		emf.close();
	}
	
	private static void demoCreation() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory( "pu-demo" );
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Client client = new Client( "G", "Pierre 9" );
		client.ajouterEmprunt( new Emprunt() );
		
		em.persist( client );
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}