import {Component, OnInit} from '@angular/core';
import {UserService} from "../user/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-changemdp',
  templateUrl: './changemdp.component.html',
  styleUrls: ['./changemdp.component.css']
})
export class ChangemdpComponent implements OnInit {
  mail!: string
  user!: any
  test: boolean=false
  msg:string=''
  constructor(private u: UserService, private route: Router) { }

  ngOnInit(): void {
  }

  change() {


    this.u.sendEmailpassword(this.mail, this.user).subscribe(
    )
    this.route.navigateByUrl("/redirection")



    /**/
  }


}
