package fr.epsi.nath.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PetStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "MANAGER_NAME")
    private String managerName;

    @Embedded
    private Address address;

    @ManyToMany(mappedBy = "petStores")
    private Set<Product> products;

    @OneToMany(mappedBy = "petStore")
    private Set<Animal> animals;

    public void addProduct(Product product) {
        this.products.add(product);
        product.getPetStores().add(this);
    }

    public PetStore(String name, String managerName, Address address) {
        this.name = name;
        this.managerName = managerName;
        this.address = address;
    }
}
