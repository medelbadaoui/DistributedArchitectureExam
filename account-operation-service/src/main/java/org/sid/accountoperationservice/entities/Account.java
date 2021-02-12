package org.sid.accountoperationservice.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import lombok.*;
import org.sid.accountoperationservice.model.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
      
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date DateCreation;
    private double solde;
    String type;
    String etat;
    @Transient @OneToMany(mappedBy = "account")
    private Collection<Operation> operations;
    private long customerID;
    @Transient private Customer customer;
    

}
