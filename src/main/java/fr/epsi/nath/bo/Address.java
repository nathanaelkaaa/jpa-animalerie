package fr.epsi.nath.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String number;
    private String street;
    private String zipCode;
    private String city;
}



