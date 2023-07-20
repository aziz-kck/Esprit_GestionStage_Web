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
public class Chaine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long chId;

    String chNom;
    @Enumerated(EnumType.STRING)
    Thematique chTheme;


}
