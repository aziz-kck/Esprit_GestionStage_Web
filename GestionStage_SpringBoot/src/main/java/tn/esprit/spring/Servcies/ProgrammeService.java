package tn.esprit.spring.Servcies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.Entities.*;
import tn.esprit.spring.Interfaces.IProgramme;
import tn.esprit.spring.Repositories.ChaineRepository;
import tn.esprit.spring.Repositories.ProgrammeRepository;
import tn.esprit.spring.Repositories.UtilisateurRepository;

import java.util.Date;

@Service
public class ProgrammeService implements IProgramme {
    @Autowired
    ProgrammeRepository programmeRepository;
    @Autowired
    ChaineRepository chaineRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Override
    public Programme ajouoterProgrammeEtChaine(Programme p) {
      //  Chaine chaine = chaineRepository.getById(chId);
        return programmeRepository.save(p);
    }

    @Override
    public Programme ajouterProgrammeEtAffecterChaine(Programme p, Long chId) {
        Chaine chaine = chaineRepository.getById(chId);
        p.getChaine();
        return programmeRepository.save(p);
    }

    @Override
    public void desaffecterProgrammeDeUtilisateur(String prNom, String usrNom) {
        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByUrsNom(usrNom);
        Programme programme = programmeRepository.findProgrammeByPrNom(prNom);
        programmeRepository.deleteProgrammeByPrNom(prNom);
    }

//    @Override
//    public void affecterProgrammeAUtilisateur(String prNom, String usrNom) {
//        Utilisateur utilisateur = utilisateurRepository.findUtilisateurByUrsNom(usrNom);
//        Programme programme = programmeRepository.findProgrammeByPrNom(prNom);
//        utilisateur.setProgramme(programme);
//
//    }


}
