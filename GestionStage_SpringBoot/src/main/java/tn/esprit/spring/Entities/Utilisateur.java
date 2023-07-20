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
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long usrId;

    String ursNom;
    @Temporal(TemporalType.TIMESTAMP)
    Date usrDateInscription;
    @Enumerated(EnumType.STRING)
    Profession profession;
    @ManyToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Programme> Programmes;
}
