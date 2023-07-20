package tn.esprit.spring.Servcies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.Entities.Programme;
import tn.esprit.spring.Entities.Utilisateur;
import tn.esprit.spring.Interfaces.IUtilisateur;
import tn.esprit.spring.Repositories.ProgrammeRepository;
import tn.esprit.spring.Repositories.UtilisateurRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurService implements IUtilisateur {
    @Autowired
    UtilisateurRepository utilisateurRepository;
@Autowired
    ProgrammeRepository programmeRepository;
    @Override
    public Utilisateur ajouterUtilisateur(Utilisateur u) {
        return utilisateurRepository.save(u);
    }
    @Override
    public void affecterProgrammeAUtilisateur(String prNom, String usrNom) {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByUrsNom(usrNom);
        Programme programme = programmeRepository.findProgrammeByPrNom(prNom);

        utilisateur.getProgrammes().add(programme);
    }
}
