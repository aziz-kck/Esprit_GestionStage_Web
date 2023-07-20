package tn.esprit.spring.Interfaces;

import tn.esprit.spring.Entities.Utilisateur;

public interface IUtilisateur {
    public Utilisateur ajouterUtilisateur(Utilisateur u);
    public void affecterProgrammeAUtilisateur(String prNom, String usrNom);
}
