package tn.esprit.spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.Entities.Chaine;

@Repository
public interface ChaineRepository extends JpaRepository<Chaine, Long> {

}
