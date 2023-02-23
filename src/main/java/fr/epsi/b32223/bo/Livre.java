package fr.epsi.b32223.bo;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "LIVRE")
public class Livre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "TITRE")
	private String titre;
	@Column(name = "AUTEUR")
	private String author;
	@ManyToMany(mappedBy = "livres")
	private Set<Emprunt> emprunts;
	public Livre() {}
	
	public Livre( String titre, String auteur ) {
		this.titre = titre;
		this.author = auteur;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId( Integer id ) {
		this.id = id;
	}
	
	public String getTitre() {
		return titre;
	}
	
	public void setTitre( String titre ) {
		this.titre = titre;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor( String auteur ) {
		this.author = auteur;
	}
	
	@Override
	public String toString() {
		return "Livre{" + "id=" + id + ", titre='" + titre + '\'' + ", auteur='" + author + '\'' + '}';
	}
}
