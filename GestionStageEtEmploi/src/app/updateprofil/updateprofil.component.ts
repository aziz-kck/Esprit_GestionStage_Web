import {Component, OnInit} from '@angular/core';
import {User} from "../user/user";
import {UserService} from "../user/user.service";
import {Router} from "@angular/router";
@Component({
  selector: 'app-updateprofil',
  templateUrl: './updateprofil.component.html',
  styleUrls: ['./updateprofil.component.css']
})
export class UpdateprofilComponent implements OnInit {

  userInfo: User = new User();
  u:User=new User();
  constructor( private userservice: UserService, private route: Router) { }
  ngOnInit(): void {
    this.userservice.getUserInfo().subscribe(
      (data) => {
        this.userInfo = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }
  logout() {
    // code to log the user out
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token'); // remove the user token from local storage
    this.route.navigate(['/signin']); // navigate the user to the login page
  }




  update()
  { console.log(this.userInfo)

    this.userservice.updateUsers(this.userInfo).subscribe(()=>this.route.navigateByUrl("/body"))
  }
}
