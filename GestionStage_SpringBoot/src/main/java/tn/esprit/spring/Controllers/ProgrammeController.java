package tn.esprit.spring.Controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.Entities.Programme;
import tn.esprit.spring.Servcies.ProgrammeService;
import tn.esprit.spring.Servcies.UtilisateurService;

@RestController
@Api(tags = "programmeController")
@RequestMapping("/programme")
public class ProgrammeController {
    @Autowired
    ProgrammeService programmeService;
    @Autowired
    UtilisateurService utilisateurService;
    @PostMapping
    @ApiOperation("addprogramme")
    public Programme ajouoterProgrammeEtChaine(@RequestBody Programme p){
        return programmeService.ajouoterProgrammeEtChaine(p);
    }
    @PostMapping("/{chId}")
    @ApiOperation("ajouterProgrammeEtAffecterChaine")
    public Programme ajouterProgrammeEtAffecterChaine(@RequestBody Programme p,@PathVariable Long chId){
        return programmeService.ajouterProgrammeEtAffecterChaine(p,chId);
    }
    @DeleteMapping("/{prNom}/{usrNom}")
    @ApiOperation("desaffecterProgrammeDeUtilisateur")
    public void desaffecterProgrammeDeUtilisateur(@PathVariable String prNom,@PathVariable String usrNom){
        programmeService.desaffecterProgrammeDeUtilisateur(prNom,usrNom);
    }
}
