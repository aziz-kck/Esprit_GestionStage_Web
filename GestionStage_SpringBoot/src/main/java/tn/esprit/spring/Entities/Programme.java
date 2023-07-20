package tn.esprit.spring.Entities;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Programme implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long prId;
    String prNom;
    @ManyToMany(mappedBy="Programmes",cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Utilisateur> Utilisateurs;

    @ManyToOne(cascade = CascadeType.ALL)
    Chaine chaine;
}
