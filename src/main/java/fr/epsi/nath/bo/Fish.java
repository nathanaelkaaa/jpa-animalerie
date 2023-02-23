package fr.epsi.nath.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fish extends Animal {

    @Column(name = "LIVING_ENV")
    private FishLivEnv livingEnv;

    public Fish( Date birth, String couleur, PetStore petStore, FishLivEnv livingEnv) {
        super( birth, couleur,petStore);
        this.livingEnv = livingEnv;
    }

}
