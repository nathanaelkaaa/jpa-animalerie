package fr.epsi.b32223.bo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Emprunt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "DATE_DEBUT")
	private LocalDateTime dateDebut;
	@Column(name = "DATE_FIN")
	private LocalDateTime dateFin;
	private int delai;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CLIENT")
	private Client client;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "compo",
			joinColumns = @JoinColumn(name = "ID_EMP", referencedColumnName = "ID"),
			inverseJoinColumns = @JoinColumn(name = "ID_LIV", referencedColumnName = "ID")
	)
	private Set<Livre> livres;
	
	{
		livres = new HashSet<>();
	}
	
	public Emprunt() {
	}
	
	public Emprunt( int delai, Client client ) {
		this.delai = delai;
		this.client = client;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId( Integer id ) {
		this.id = id;
	}
	
	public LocalDateTime getDateDebut() {
		return dateDebut;
	}
	
	public void setDateDebut( LocalDateTime dateDebut ) {
		this.dateDebut = dateDebut;
	}
	
	public LocalDateTime getDateFin() {
		return dateFin;
	}
	
	public void setDateFin( LocalDateTime dateFin ) {
		this.dateFin = dateFin;
	}
	
	public int getDelai() {
		return delai;
	}
	
	public void setDelai( int delai ) {
		this.delai = delai;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient( Client client ) {
		if (this.client != null) {
			this.client.getEmprunts().remove( this );
		}
		
		this.client = client;
		
		if (this.client != null) {
			this.client.getEmprunts().add( this );
		}
	}
	
	public Set<Livre> getLivres() {
		return livres;
	}
	
	public void setLivres( Set<Livre> livres ) {
		this.livres = livres;
	}
	
	@Override
	public String toString() {
		return "Emprunt{" + "id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", delai=" + delai + '}';
	}
}
