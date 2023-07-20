package tn.esprit.spring.Interfaces;

import tn.esprit.spring.Entities.Profession;
import tn.esprit.spring.Entities.Programme;
import tn.esprit.spring.Entities.Thematique;

import java.util.Date;

public interface IProgramme {
    public Programme ajouoterProgrammeEtChaine(Programme p);
    public Programme ajouterProgrammeEtAffecterChaine(Programme p, Long chId);

//    public void affecterProgrammeAUtilisateur(String prNom, String usrNom);

    public void desaffecterProgrammeDeUtilisateur(String prNom, String usrNom);


}
