package tn.esprit.spring.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.Entities.Chaine;
import tn.esprit.spring.Entities.Programme;

@Repository
public interface ProgrammeRepository extends JpaRepository<Programme, Long> {
    Programme findProgrammeByPrNom(String prNom);

    Programme deleteProgrammeByPrNom(String prNom);
}
