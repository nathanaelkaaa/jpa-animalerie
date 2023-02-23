package fr.epsi.b32223.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	@Column(name = "NOM")
	private String nom;
	@Column(name = "PRENOM")
	private String prenom;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Emprunt> emprunts;
	
	{
		emprunts = new HashSet<>();
	}
	
	public Client( String nom, String prenom ) {
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public void ajouterEmprunt(Emprunt emprunt) {
		
		emprunt.setClient( this );
	}
	
	
	@Override
	public String toString() {
		return "Client{" + "id=" + id + ", nom='" + nom + '\'' + ", prenom='" + prenom + '\'' + '}';
	}
}
