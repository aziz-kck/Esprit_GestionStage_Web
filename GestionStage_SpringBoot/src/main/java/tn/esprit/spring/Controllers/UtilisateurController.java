package tn.esprit.spring.Controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.Entities.Utilisateur;
import tn.esprit.spring.Servcies.ProgrammeService;
import tn.esprit.spring.Servcies.UtilisateurService;

@RestController
@Api(tags = "utilisateurController")
@RequestMapping("/utilisateur")
public class UtilisateurController {
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    ProgrammeService programmeService;
    @PostMapping
    @ApiOperation("addutilisateur")
    public Utilisateur ajouterUtilisateur(@RequestBody Utilisateur u){
        return utilisateurService.ajouterUtilisateur(u);
    }
    @PostMapping("/{prNom}/{usrNom}")
    @ApiOperation("affecterProgrammeAUtilisateur")
    public void affecterProgrammeAUtilisateur(@PathVariable String prNom, @PathVariable String usrNom){
        utilisateurService.affecterProgrammeAUtilisateur(prNom,usrNom);
    }
}
